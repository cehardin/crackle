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

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.crackle.Address;

/**
 * The controller for multiple workers.
 * @author Chad
 */
public interface Controller extends Remote {

    /**
     * Find the worker than contains the actor with the specified address.
     * @param address The address to look for.
     * @return The worker for that address or null if none exists.
     * @throws RemoteException 
     * @throws NullPointerException If address is null
     */
    Worker findWorker(Address address) throws RemoteException, NullPointerException;
    
    /**
     * Notifies which worker should be used to create the next actor.
     * @return The worker, never null.
     * @throws RemoteException 
     */
    Worker getCreationWorker() throws RemoteException;
    
    /**
     * Determine an any workers contain an actor with the specified address.
     * @param address The address to look for.
     * @return true if a worker has an actor with that address.
     * @throws RemoteException 
     * @throws NullPointerException If address is null
     */
    boolean contains(Address address) throws RemoteException, NullPointerException;

}
