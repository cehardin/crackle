package org.crackle;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Chad
 */
public interface Place extends Serializable {
    
    void start();
    
    void stop();
    
    Address create(Class<? extends Actor> type);
    
    void send(Address address, Message message);
    
    Set<Address> getActors();
    
    Iterable<Place> getSubPlaces();
}
