package org.crackle.impl;

import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;




/**
 * An engine runs actors and can be listened to for actor events.
 * @author Chad
 */
public interface Engine {
    
    /**
     * Add a listener
     * @param listener The listener to add
     * @throws NullPointerException If listener is null 
     */
    void addListener(EngineListener listener) throws NullPointerException;
    
    /**
     * Remove a listener
     * @param listener The listener to remove
     * @throws NullPointerException If listener is null
     */
    void removeListener(EngineListener listener) throws NullPointerException;
    
     /**
     * Creates an actor and returns its address.
     * @param behavior The initial behavior of the actor
     * @return The address of the newly created actor, never null
     * @throws NullPointerException If behavior is null
     */
    Address create(Behavior behavior) throws NullPointerException;
    
    /**
     * Send a message to an actor.
     * @param address The address of the actor
     * @param message The message to send
     * @return true if the address is known by the engine
     * @throws NullPointerException If address or message are null
     */
    boolean send(Address address, Message message) throws NullPointerException;
    
    /**
     * Determine if the engine is aware of an actor with that address.
     * @param address The address to check for.
     * @return true if the address is known by the engine
     * @throws NullPointerException If address is null
     */
    boolean contains(Address address) throws NullPointerException;
}
