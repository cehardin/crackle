package org.crackle;


/**
 * A place is where actors reside.
 * @author Chad
 */
public interface Place {
    
     /**
     * Creates an actor and returns its address.
     * @param behavior The initial behavior of the actor
     * @return The address of the newly created actor
     */
    Address create(Behavior behavior);
    
    /**
     * Send a message to an actor.
     * @param address The address of the actor
     * @param message The message to send
     */
    void send(Address address, Message message);
}
