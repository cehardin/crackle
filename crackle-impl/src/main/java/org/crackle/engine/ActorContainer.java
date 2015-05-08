package org.crackle.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Resource;
import org.crackle.Actor;
import org.crackle.Address;
import org.crackle.Context;

/**
 *
 * @author Chad
 */
public class ActorContainer<INTERFACE extends Actor, IMPLEMENTATION extends INTERFACE> {

    private final INTERFACE actorProxy;
    private final IMPLEMENTATION actorImplemntation;
    private final AtomicBoolean lock;
    private final AtomicBoolean terminate;
    private final ContainerContext context;
    private final Queue<ActorCommand> commandQueue;

    private class ContainerContext implements Context {

        @Override
        public void terminate() throws IllegalStateException {
            terminate.set(true);
        }

        @Override
        public <T extends Actor> Address<T> create(Class<T> type) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class ActorInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(proxy != actorProxy) {
                throw new IllegalStateException();
            }
            processCommand(new ActorInvokeCommand(actorImplemntation, method, args));
            return null;
        }   
    }

    public ActorContainer(final Class<INTERFACE> actorInterfaceClass, final IMPLEMENTATION actorImplementation) {
        final Class[] classes = {actorInterfaceClass};
        final InvocationHandler invocationHandler = new ActorInvocationHandler();
        final Object proxy = Proxy.newProxyInstance(null, classes, invocationHandler);
        
        this.actorProxy = actorInterfaceClass.cast(proxy);
        this.actorImplemntation = Objects.requireNonNull(actorImplementation);
        this.lock = new AtomicBoolean(false);
        this.terminate = new AtomicBoolean(false);
        this.context = new ContainerContext();
        this.commandQueue = new ConcurrentLinkedQueue<>();

        //look for resource annotation and add context
        for (final Field field : actorImplementation.getClass().getFields()) {
            if (!Modifier.isStatic(field.getModifiers()))  {
                if (field.isAnnotationPresent(Resource.class)) {
                    if (Context.class.equals(field.getType())) {
                        field.setAccessible(true);
                        try {
                            field.set(actorImplementation, this.context);
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            throw new RuntimeException("Could not set actor context", ex);
                        }
                    }
                }
            }
        }
    }

    public void processCommand(final ActorCommand command) {
        tryToProcess(() -> {
            drainQueue();
            command.run();
        });
    }

    public void processQueue() {
        tryToProcess(this::drainQueue);
    }

    private boolean tryToProcess(final Runnable r) {
        final boolean locked = lock.compareAndSet(false, true);
        if (locked) {
            try {
                r.run();
            } finally {
                lock.set(false);
            }
        }
        return locked;
    }

    private void drainQueue() {
        final Iterator<ActorCommand> commands = commandQueue.iterator();

        while (commands.hasNext()) {
            final ActorCommand command = commands.next();
            commands.remove();
            command.run();
        }
    }
}
