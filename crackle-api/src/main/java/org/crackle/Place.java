package org.crackle;

import java.io.Serializable;
import java.util.Set;

/**
 * A place is where actors reside.
 * @author Chad Hardin
 */
public interface Place extends ActorCreator, MessageSender, Serializable {
    
    /**
     * Start the place
     */
    void start();
    
    /**
     * Stop the place
     */
    void stop();
    
    /**
     * Get the actors in this place
     * @return The actors in this place
     */
    Set<Address<?>> getActors();
}
