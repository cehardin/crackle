package org.crackle.impl;

import java.util.EventListener;

/**
 * Listens for events from an {@link Engine}
 * @author Chad
 */
public interface EngineListener extends EventListener {

    /**
     * Called when an engine creates an actor.
     * @param event The engine event, never null.
     */
    void actorCreated(EngineEvent event);
    
    void messageUndeliverable(EngineEvent event);
}
