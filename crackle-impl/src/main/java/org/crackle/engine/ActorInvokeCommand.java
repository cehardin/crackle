package org.crackle.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import org.crackle.Actor;

/**
 *
 * @author Chad
 */
public class ActorInvokeCommand implements ActorCommand {
    private final Actor actor;
    private final Method method;
    private final Object[] arguments;

    public ActorInvokeCommand(
            final Actor actor, 
            final Method method, 
            final Object[] arguments) {
        this.actor = Objects.requireNonNull(actor);
        this.method = Objects.requireNonNull(method);
        this.arguments = Objects.requireNonNull(arguments);
    }

    @Override
    public void run() {
        try {
            method.invoke(actor, arguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(String.format("Could not invoke method %s on %s", method, actor), e);
        }
    }

    
}
