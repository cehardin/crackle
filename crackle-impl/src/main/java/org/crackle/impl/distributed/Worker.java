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
import org.crackle.Behavior;
import org.crackle.Message;

/**
 * A worker, controlled by a {@link Conroller}
 * @author Chad
 */
public interface Worker extends Remote {
    
    /**
     * Add a listener.
     * @param listener The listener
     * @throws RemoteException
     * @throws NullPointerException If listener is null
     */
    void addListener(WorkerListener listener) throws RemoteException, NullPointerException;
    
    /**
     * Remove a listener
     * @param listener The listener
     * @throws RemoteException
     * @throws NullPointerException If listener is null
     */
    void removeListener(WorkerListener listener) throws RemoteException, NullPointerException;
    
    /**
     * Create an actor
     * @param behavior The initial behavior of he actor
     * @return The address of the new actor, never null
     * @throws RemoteException
     * @throws NullPointerException If behavior is null
     */
    Address create(Behavior behavior) throws RemoteException, NullPointerException;

    /**
     * Sends a message to an actor
     * @param address The address of the actor
     * @param message The message to send
     * @return true if an actor with that address was found
     * @throws RemoteException
     * @throws NullPointerException  If address or message are null
     */
    boolean send(Address address, Message message) throws RemoteException, NullPointerException;
    
    /**
     * Determines this worker has an actor with the specified address.
     * @param address The address of the actor
     * @return true if an actor with that address is contained
     * @throws RemoteException
     * @throws NullPointerException If address is null
     */
    boolean contains(Address address) throws RemoteException, NullPointerException;

}
