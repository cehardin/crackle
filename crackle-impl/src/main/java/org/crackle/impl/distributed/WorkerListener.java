package org.crackle.impl.distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.crackle.Address;

/**
 *
 * @author Chad
 */
public interface WorkerListener extends Remote {
    
    void actorCreated(Worker worker, Address address) throws RemoteException;
}
