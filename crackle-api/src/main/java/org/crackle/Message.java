package org.crackle;

import java.io.Serializable;

/**
 *
 * @author Chad
 */
public interface Message extends Serializable, Cloneable {
    Message clone();
}
