package org.crackle.impl;

import java.util.EventObject;
import java.util.Objects;
import java.util.Optional;
import org.crackle.Address;
import org.crackle.Message;

/**
 * Engine events that passed to instance of {@link EngineListener}.
 * @author Chad
 */
public class EngineEvent extends EventObject {
    private final Engine engine;
    private final Address address;
    private final Optional<Message> message;

    public EngineEvent(Engine engine, Address address, Optional<Message> message) {
        super(engine);
        this.engine = Objects.requireNonNull(engine);
        this.address = Objects.requireNonNull(address);
        this.message = Objects.requireNonNull(message);
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

    public Optional<Message> getMessage() {
        return message;
    }
}
