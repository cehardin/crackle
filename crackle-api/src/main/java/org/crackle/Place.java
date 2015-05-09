package org.crackle;

import java.io.Serializable;

/**
 *
 * @author Chad
 */
public interface Place extends Serializable {
    
    void start();
    
    void stop();
    
    Address create(Class<? extends Actor> type);
    
    void send(Address address, Message message);
}
