/**
 * This file is part of crackle-impl.
 *
 * crackle-impl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-impl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-impl.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.crackle.impl.distributed;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import org.crackle.Address;
import org.crackle.Message;

/**
 * 
 * @author Chad
 */
public class ControllerImpl implements Controller, WorkerListener {
    private final List<Worker> workers;
    private final ConcurrentMap<Address, Worker> addressWorkerMap = new ConcurrentHashMap<>();
    
    public ControllerImpl(Collection<Worker> workers) {
        this.workers = new ArrayList<>(workers);
    }
    
    public void start() throws RemoteException {
        for(final Worker worker : workers) {
            worker.addListener(this);
        }
    }
    
    public void stop() throws RemoteException {
        for(final Worker worker : workers) {
            worker.removeListener(this);
        }
    }

    @Override
    public void actorCreated(Worker source, Address address) throws RemoteException {
        addressWorkerMap.put(Objects.requireNonNull(address), Objects.requireNonNull(source));
    }

    @Override
    public void messageUndeliverable(Worker source, Address address, Message message) throws RemoteException {
        final Optional<Worker> worker = Optional.ofNullable(findWorker(address));
        
        if(worker.isPresent()) {
            worker.get().send(address, message);
        }
    }
    
    @Override
    public Worker findWorker(Address address) throws RemoteException {
        return find(address).orElse(null);
    }

    @Override
    public Worker getCreationWorker() throws RemoteException {
        final int index = ThreadLocalRandom.current().nextInt(workers.size());
        
        return workers.get(index);
    }
    
    

    @Override
    public boolean contains(Address address) throws RemoteException {
        return find(address).isPresent();
        
    }
    
    private Optional<Worker> find(Address address) throws RemoteException {
        Optional<Worker> found = Optional.ofNullable(addressWorkerMap.get(Objects.requireNonNull(address)));
        
        if(!found.isPresent()) {
            for(final Worker worker : workers) {
                if(worker.contains(address)) {
                    addressWorkerMap.put(address, worker);
                    found = Optional.of(worker);
                    break;
                }
            }
        }
        
        return found;
    }
    
}
