/**
 * This file is part of crackle-impl.
 *
 * crackle-impl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-impl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-impl.  If not, see <http://www.gnu.org/licenses/>.
 */
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
        final EngineEvent event = new EngineEvent(this, address, Optional.empty());
        
        actors.put(address, actor);
        
        for(final EngineListener listener : listeners) {
            listener.actorCreated(event);
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
        
        if(!contains) {
            final EngineEvent event = new EngineEvent(this, address, Optional.of(message.clone()));
            
            for(final EngineListener listener : listeners) {
                listener.messageUndeliverable(event);
            }
        }
        return contains;
    }

    @Override
    public boolean contains(Address address) {
        return actors.containsKey(Objects.requireNonNull(address));
    }

}
