/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.engine;

/**
 *
 * @author chad
 */
public interface ActorEngine {
    
    enum State {
        Stopped,
        Starting,
        Running,
        Stopping
    }
    
    void start();
    void stop();
    State getState();
    
}
