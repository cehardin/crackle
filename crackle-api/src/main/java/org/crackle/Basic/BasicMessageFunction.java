package org.crackle.Basic;

import java.util.function.BiFunction;
import org.crackle.Context;

/**
 * A function that changes the value in a {@link BasicActor} via a {@link BasicMessage}
 * @author Chad Hardin
 * @param <T> The type of object to process
 */
@FunctionalInterface
public interface BasicMessageFunction<T> extends BiFunction<Context, T, T> {
    
    /**
     * Process the value and return the new value
     * @param context The context
     * @param t The value
     * @return The new value
     */
    @Override
    T apply(Context context, T t);
}
