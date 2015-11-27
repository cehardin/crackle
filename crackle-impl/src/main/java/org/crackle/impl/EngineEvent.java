package org.crackle.impl;

import java.util.EventObject;
import java.util.Objects;
import org.crackle.Address;

/**
 * Engine events that passed to instance of {@link EngineListener}.
 * @author Chad
 */
public class EngineEvent extends EventObject {
    private final Engine engine;
    private final Address address;

    public EngineEvent(Engine engine, Address address) {
        super(engine);
        this.engine = Objects.requireNonNull(engine);
        this.address = Objects.requireNonNull(address);
    }

    /**
     * Get the engine.
     * @return  The engine, never null.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Get the address
     * @return The address, never null
     */
    public Address getAddress() {
        return address;
    }
    
}
