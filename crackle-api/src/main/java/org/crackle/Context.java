package org.crackle;

/**
 * The context for an actor, used by the actor when it is called.
 * @author Chad Hardin
 */
public interface Context extends ActorCreator, MessageSender {
    
    /**
     * Called by an actor to terminate itself
     * @throws IllegalStateException If suspend or terminate has already been invoked.
     */
    void terminate() throws IllegalStateException;
    
    /**
     * Called by an actor to suspend itself
     * @throws IllegalStateException  If suspend or terminate has already been invoked.
     */
    void suspend() throws IllegalStateException;
}
