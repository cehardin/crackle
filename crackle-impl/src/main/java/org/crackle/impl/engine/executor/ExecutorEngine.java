package org.crackle.impl.engine.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;
import org.crackle.impl.AddressFactory;
import org.crackle.impl.Engine;
import org.crackle.impl.EngineEvent;
import org.crackle.impl.EngineListener;

/**
 * An engine that uses a Java {@link Executor} to process actor messages.
 * @author Chad
 */
public class ExecutorEngine implements Engine {
    private final AddressFactory addressFactory;
    private final List<EngineListener> listeners = new ArrayList<>();
    private final Map<Address, ExecutorActor> actors = Collections.synchronizedMap(new WeakHashMap<>());
    private final Executor executor;

    public ExecutorEngine(Executor executor, AddressFactory addressFactory) {
        this.executor = Objects.requireNonNull(executor);
        this.addressFactory = Objects.requireNonNull(addressFactory);
    }

    @Override
    public void addListener(EngineListener listener) {
        listeners.add(Objects.requireNonNull(listener));
    }

    @Override
    public void removeListener(EngineListener listener) {
        listeners.remove(Objects.requireNonNull(listener));
    }

    @Override
    public Address create(final Behavior behavior) {
        final Address address = addressFactory.createAddress();
        final ExecutorActor actor = new ExecutorActor(behavior.clone());

        actors.put(address, actor);
        
        for(final EngineListener listener : listeners) {
            listener.actorCreated(new EngineEvent(this, address));
        }
        
        return address;
    }

    @Override
    public boolean send(final Address address, final Message message) {
        final Message clonedMessage = message.clone();
        final boolean contains = contains(address);
        
        executor.execute(() -> {
            final Optional<ExecutorActor> actor = Optional.ofNullable(actors.get(address));
            
            if (actor.isPresent()) {
                actor.get().process(address, ExecutorEngine.this, clonedMessage);
            }
        });
        
        return contains;
    }

    @Override
    public boolean contains(Address address) {
        return actors.containsKey(Objects.requireNonNull(address));
    }

}
