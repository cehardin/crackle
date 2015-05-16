package org.crackle.impl.simple;

import java.util.Queue;
import org.crackle.Actor;
import org.crackle.Message;

/**
 * A reord for an actor that contains the actual actor object, the message queue,
 * and the actor's context.
 * @author Chad Hardin
 */
public class ActorRecord {
    private final Actor<?> actor;
    private final Queue<Message> messages;
    private final SimpleContext context;

    /**
     * 
     * @param actor
     * @param messages
     * @param context 
     */
    public ActorRecord(Actor<?> actor, Queue<Message> messages, SimpleContext context) {
        this.actor = actor;
        this.messages = messages;
        this.context = context;
    }

    /**
     * Get the actor
     * @return The actor
     */
    public Actor<?> getActor() {
        return actor;
    }

    /**
     * Get the actor's messages
     * @return The actor's messages
     */
    public Queue<Message> getMessages() {
        return messages;
    }

    /**
     * Get the actor's context
     * @return The actor's context
     */
    public SimpleContext getContext() {
        return context;
    }
}
