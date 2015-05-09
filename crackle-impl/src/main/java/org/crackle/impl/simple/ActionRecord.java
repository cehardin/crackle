package org.crackle.impl.simple;

import org.crackle.Address;

/**
 *
 * @author Chad
 */
public class ActionRecord {
    public enum Type {
        Message,
        Terminate
    }
    
    private final Type type;
    private final Address address;
    

    public ActionRecord(Type type, Address address) {
        this.type = type;
        this.address = address;
    }

    public Type getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }
}
