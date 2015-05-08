package org.crackle;


/**
 * An address for an actor
 * @author Chad
 * @param <T>
 */
public interface Address<T extends Actor> {
    T lookup();
}
