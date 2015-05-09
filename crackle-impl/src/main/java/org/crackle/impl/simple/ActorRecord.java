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

    public ActorRecord(Actor actor, Queue<Message> messages) {
        this.actor = actor;
        this.messages = messages;
    }

    public Actor getActor() {
        return actor;
    }

    public Queue<Message> getMessages() {
        return messages;
    }
    
    
}
