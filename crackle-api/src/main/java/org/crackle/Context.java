package org.crackle;

/**
 *
 * @author Chad
 */
public interface Context {
    void terminate() throws IllegalStateException;
    
    void suspend() throws IllegalStateException;
    
    Address create(Class<? extends Actor> type);
    
    void send(Address address, Message message);
}
