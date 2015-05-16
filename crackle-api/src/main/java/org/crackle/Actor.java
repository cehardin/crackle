package org.crackle;

import java.io.Serializable;

/**
 * An actor processes messages and be suspended, resumed, and terminated.  Only one
 * thread at a time will ever call any of these methods.
 * @author Chad Hardin
 * @param <M> The type of messages this actor processes.
 */
public interface Actor<M extends Message> extends Serializable {
    
    /**
     * Initialize, only called once, and before any other method is called.
     * @param context The actor's context
     * @throws Exception If anything goes wrong, resulting in the termination of this actor
     */
    void initialize(Context context) throws Exception;
    
    /**
     * Called for each message to be processed.
     * @param context  The actor's context
     * @param message The message
     * @throws Exception If anything goes wrong, resulting in the termination of this actor
     */
    void message(Context context, M message) throws Exception;
    
    /**
     * Called right before this actor is suspended.
     * @param context  The actor's context
     * @throws Exception If anything goes wrong, resulting in the termination of this actor
     */
    void suspend(Context context) throws Exception;
    
    /**
     * Called right after this actor is resumed from being suspended.
     * @param context  The actor's context
     * @throws Exception If anything goes wrong, resulting in the termination of this actor
     */
    void resume(Context context) throws Exception;
    
    /**
     * Called right before this actor is terminated.  Only called once and no other method is called after.
     * @param context The actor's context
     * @throws Exception If anything goes wrong, resulting in the termination of this actor
     */
    void terminate(Context context) throws Exception;
}
