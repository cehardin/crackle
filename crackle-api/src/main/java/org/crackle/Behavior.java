package org.crackle;

import java.io.Serializable;

/**
 * The behavior for an actor.
 * @author Chad Hardin
 */
public interface Behavior extends Serializable, Cloneable {
    
    void process(Context context);
    
    Behavior clone();
}
