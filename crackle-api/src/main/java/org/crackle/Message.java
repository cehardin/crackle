package org.crackle;

import java.io.Serializable;

/**
 * A message s sent to and processed by an actor.
 * @author Chad Hardin
 */
public interface Message extends Serializable, Cloneable {
    
    /**
     * Clone the message
     * @return A clone of the message
     */
    Message clone();
}
