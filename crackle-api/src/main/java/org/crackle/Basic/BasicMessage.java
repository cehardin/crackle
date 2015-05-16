package org.crackle.Basic;

import org.crackle.Message;

/**
 * Simply contains a {@link BasicMessageFunction}, which is applied
 * to a {@link BasicActor}
 * @author Chad Hardin
 */
public final class BasicMessage<T> implements Message {
    private final BasicMessageFunction<T> function;

    /**
     * 
     * @param function 
     */
    public BasicMessage(BasicMessageFunction<T> function) {
        this.function = function;
    }

    /**
     * 
     * @return 
     */
    public BasicMessageFunction<T> getFunction() {
        return function;
    }

    /**
     * 
     * @return 
     */
    @Override
    public BasicMessage clone() {
        return this;
    }
}
