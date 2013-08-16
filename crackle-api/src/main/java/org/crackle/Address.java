package org.crackle;

import java.io.Serializable;

/**
 * An address for an actor
 * @author Chad
 */
public interface Address extends Serializable, Cloneable {
    Address clone();
}
