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
