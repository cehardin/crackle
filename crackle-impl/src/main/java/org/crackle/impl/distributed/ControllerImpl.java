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
    public void actorCreated(Worker worker, Address address) throws RemoteException {
        addressWorkerMap.put(Objects.requireNonNull(address), Objects.requireNonNull(worker));
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
