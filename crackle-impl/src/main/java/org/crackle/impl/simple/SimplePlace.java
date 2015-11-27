package org.crackle.impl.simple;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;
import org.crackle.Place;

/**
 *
 * @author Chad
 */
public class SimplePlace implements Place {

    private final Map<Address, SimpleActor> actors = Collections.synchronizedMap(new WeakHashMap<>());
    private final Executor executor;

    public SimplePlace(Executor executor) {
        this.executor = executor;
    }

    @Override
    public Address create(final Behavior behavior) {
        final Address address = new SimpleAddress();
        final SimpleActor actor = new SimpleActor(behavior.clone());

        actors.put(address, actor);
        
        return address;
    }

    @Override
    public void send(final Address address, final Message message) {
        final Message clonedMessage = message.clone();
        
        executor.execute(() -> {
            final Optional<SimpleActor> actor = Optional.ofNullable(actors.get(address));
            
            if (actor.isPresent()) {
                actor.get().process(address, SimplePlace.this, clonedMessage);
            }
        });
    }

}
