package org.crackle.impl.simple;

import java.util.Queue;
import org.crackle.Actor;
import org.crackle.Message;

/**
 *
 * @author Chad
 */
public class ActorRecord {
    private final Actor actor;
    private final Queue<Message> messages;
    private final SimpleContext context;

    public ActorRecord(Actor actor, Queue<Message> messages, SimpleContext context) {
        this.actor = actor;
        this.messages = messages;
        this.context = context;
    }

    public Actor getActor() {
        return actor;
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    public SimpleContext getContext() {
        return context;
    }
}
