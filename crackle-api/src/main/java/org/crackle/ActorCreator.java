package org.crackle;

/**
 * Creates actors
 * @author Chad Hardin
 */
public interface ActorCreator {

    /**
     * Creates an actor and returns its address.
     * @param <M> The type of messages the actor will process.
     * @param <A> The type of actor to create
     * @param type The class of the actor
     * @return The address of the newly created actor
     */
    <M extends Message, A extends Actor<M>> Address<M> create(Class<A> type);
}
