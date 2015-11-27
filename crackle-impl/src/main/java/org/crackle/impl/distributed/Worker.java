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
