package org.crackle;

/**
 * Creates Actors
 * @author Chad
 */
public interface Creator {
    /**
     * Create an actor of the specified behavior.
     * @param type The type of behavior for the actor
     * @return The address of the newly created actor.
     */
    Address createActor(Class<Behavior> type);
}
