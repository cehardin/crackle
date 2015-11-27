package org.crackle.impl.distributed;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;

/**
 * Interacts with a {@link Controller} and many {@link Worker} objects
 * to create and send messages to actors in a distributed way.
 * @author Chad
 */
public class Client {

    private final Controller controller;
    private final ConcurrentMap<Address, Worker> addressWorkerMap = new ConcurrentHashMap<>();

    /**
     * Create with a controller
     * @param controller The controller
     * @throws NullPointerException If controller is null
     */
    public Client(Controller controller) throws NullPointerException {
        this.controller = Objects.requireNonNull(controller);
    }

    /**
     * Create a new actor
     * @param behavior The behavior of the actor
     * @return The address of the actor, never null
     * @throws RemoteException
     * @throws NullPointerException  If behavior is null
     */
    public Address create(Behavior behavior) throws RemoteException, NullPointerException {
        final Worker worker = Objects.requireNonNull(controller.getCreationWorker());
        final Address address = Objects.requireNonNull(worker.create(Objects.requireNonNull(behavior)));

        addressWorkerMap.put(address, worker);

        return address;
    }

    /**
     * Send a message to an actor.
     * @param address The address of the actor
     * @param message The message to send
     * @return true if the actor was found
     * @throws RemoteException 
     * @throws NullPointerException If address or message were null
     */
    public boolean send(Address address, Message message) throws RemoteException, NullPointerException {
        final Optional<Worker> worker = find(address);
        final boolean found;
        
        Objects.requireNonNull(message);

        if (worker.isPresent()) {
            found = worker.get().send(address, message);
        } else {
            found = false;
        }

        return found;
    }

    /**
     * Determine if an actor with a specified address exists
     * @param address The address to look for
     * @return if contained
     * @throws RemoteException
     * @throws NullPointerException If address is null
     */
    public boolean contains(Address address) throws RemoteException, NullPointerException {
        final Optional<Worker> worker = find(address);
        boolean contains;

        if (worker.isPresent()) {
            contains = worker.get().contains(address);
        } else {
            contains = false;
        }

        return contains;

    }

    /**
     * 
     * @param address
     * @return
     * @throws RemoteException 
     */
    private Optional<Worker> find(Address address) throws RemoteException, NullPointerException {
        Optional<Worker> found = Optional.ofNullable(addressWorkerMap.get(Objects.requireNonNull(address)));

        if (!found.isPresent()) {
            found = Optional.ofNullable(controller.findWorker(address));
            if (found.isPresent()) {
                addressWorkerMap.put(address, found.get());
            }
        }

        return found;
    }
}
