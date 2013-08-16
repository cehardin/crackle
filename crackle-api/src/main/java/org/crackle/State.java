package org.crackle;

import java.io.Serializable;

/**
 *
 * @author Chad
 */
public interface State extends Serializable, Cloneable {
    State clone();
}
