package org.crackle;

/**
 * Given to a {@link Behavior} when it processes a {@link Message}.
 * @author Chad Hardin
 * @see Behavior#process(org.crackle.Context) 
 */
public interface Context {
   
    /**
     * Get the address for the actor being processed.
     * @return The address of the actor being processed, never null.
     */
    Address getAddress();
    
    /**
     * Change the behavior of the actor for the next message to be processed.  
     * An actor may change its behavior
     * while processing a message. However, it can only change in once. If
     * the behavior is not changed, the current behavior is used for the next 
     * message.
     * @param behavior The behavior to use for the next message processed.
     * @throws IllegalStateException If the behavior is changed more than once.
     * @throws NullPointerException If the behavior is null
     */
    void change(Behavior behavior) throws IllegalStateException, NullPointerException;
    
    
     /**
     * Creates an actor and returns its address.
     * @param behavior The initial behavior of the actor
     * @return The address of the newly created actor, never null
     * @throws NullPointerException If the behavior is null
     */
    Address create(Behavior behavior) throws NullPointerException;
    
    /**
     * Send a message to an actor.
     * @param address The address of the actor
     * @param message The message to send
     * @throws NullPointerException If address or message are null
     */
    void send(Address address, Message message) throws NullPointerException;
}
