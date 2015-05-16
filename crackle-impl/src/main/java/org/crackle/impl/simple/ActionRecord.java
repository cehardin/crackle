package org.crackle.impl.simple;

import org.crackle.Address;

/**
 * A record of an action for an actor
 * @author Chad Hardin
 */
public class ActionRecord {
    
    /**
     * The type of action
     */
    public enum Type {
        Message,
        Terminate
    }
    
    private final Type type;
    private final Address<?> address;
    
    /**
     * 
     * @param type The type of action
     * @param address The address of the actor
     */
    public ActionRecord(Type type, Address<?> address) {
        this.type = type;
        this.address = address;
    }

    /**
     * Get the type of action
     * @return The type of action
     */
    public Type getType() {
        return type;
    }

    /**
     * Get the address of the actor
     * @return The address of the actor
     */
    public Address<?> getAddress() {
        return address;
    }
}
