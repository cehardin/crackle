package org.crackle;

import java.io.Serializable;

/**
 * A message is sent to and processed by an actor.
 * @author Chad Hardin
 */
public interface Message extends Serializable, Cloneable {
    
    /**
     * Clone the message.  Messages must be able to clone themselves.  If the message
     * is immutable, it may return itself.
     * @return A clone of the message
     */
    Message clone();
}
