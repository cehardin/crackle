package org.crackle.Basic;

import java.io.Serializable;
import org.crackle.Actor;
import org.crackle.Context;

/**
 * A basic actor contains exactly one value; that value is changed by
 * sending it a {@link BasicMessage}.  The value must be {@link Serializable}
 * @author Chad Hardin
 * @param <T> The type of object this actor contains.
 */
public final class BasicActor<T> implements Actor<BasicMessage<T>> {

    private T value = null;
    
    /**
     * Sets the value to null.
     * @param context
     * @throws Exception 
     */
    @Override
    public void initialize(Context context) throws Exception {
        value = null;
    }

    /**
     * Changes the value
     * @param context
     * @param message
     * @throws Exception 
     */
    @Override
    public void message(Context context, BasicMessage<T> message) throws Exception {
        value = message.getFunction().apply(context, value);
    }

    /**
     * Does nothing
     * @param context
     * @throws Exception 
     */
    @Override
    public void suspend(Context context) throws Exception {
    }

    /**
     * Does nothing
     * @param context
     * @throws Exception 
     */
    @Override
    public void resume(Context context) throws Exception {
    }

    /**
     * Sets the value to null
     * @param context
     * @throws Exception 
     */
    @Override
    public void terminate(Context context) throws Exception {
        value = null;
    }
    
}
