package org.crackle;

import java.io.Serializable;


/**
 * An address for an actor
 * @author Chad Hardin
 * @param <M> The type of messages the actor processes.
 */
public interface Address<M extends Message> extends Serializable {
}
