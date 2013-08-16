package org.crackle;

import java.io.Serializable;

/**
 *
 * @author Chad
 */
public interface Behavior<S extends State, M extends Message> extends Serializable {
    static interface Context<S extends State, M extends Message> {
        M getMessage();
        S getState();
        void setState(S state);
        Sender getSender();
        Behavior<S, M> getBehavior();
        void setBehavior(Behavior<S,M> behavior);
        void stop();
        Creator getCreator();
    }
    
    void react(Context<S,M> context);
}
