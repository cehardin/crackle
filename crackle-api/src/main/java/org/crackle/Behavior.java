package org.crackle;

import java.io.Serializable;

/**
 * The behavior for an actor. An actor may potential have many different
 * behaviors over the course of its life.
 * @author Chad Hardin
 */
public interface Behavior extends Serializable, Cloneable {
    
    /**
     * Process a message.  An actor may create other actors, send messages,
     * and optionally change its behavior for the next message.
     * @param context The context to process under, never null
     */
    void process(Context context);
    
    /**
     * Behaviors must be able to clone themselves. Immutable behaviors
     * may simple return themselves.
     * @return The close of the behavior
     */
    Behavior clone();
}
