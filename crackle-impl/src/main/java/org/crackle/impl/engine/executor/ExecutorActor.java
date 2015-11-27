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

import java.util.Objects;
import java.util.Optional;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.impl.Engine;

/**
 * An actor class used by the {@link ExecutorEngine}.
 * @author Chad
 */
class ExecutorActor {

    private Behavior behavior;
    private Optional<Behavior> nextBehavior;

    public ExecutorActor(Behavior behavior) {
        this.behavior = Objects.requireNonNull(behavior);
        this.nextBehavior = Optional.empty();
    }

    /**
     * Process a single message and maintain a lock oon this actor object
     * while doing so.
     * @param address The address of the actor being processed.
     * @param engine The engine processing the actor.
     * @param message The message for the actor to process.
     */
    public synchronized void process(Address address, Engine engine, Message message) {
        
        behavior.process(new Context() {
            @Override
            public Address getAddress() {
                return address;
            }

            @Override
            public void change(Behavior behavior) throws IllegalStateException, NullPointerException {
                if(nextBehavior.isPresent()) {
                    throw new IllegalStateException();
                }
                else {
                    nextBehavior = Optional.of(behavior.clone());
                }
            }

            @Override
            public Address create(Behavior behavior) {
                return engine.create(behavior);
            }

            @Override
            public void send(Address address, Message message) {
                engine.send(address, message);
            }
        });
        
        if(nextBehavior.isPresent()) {
            behavior = nextBehavior.get();
            nextBehavior = Optional.empty();
        } else {
            behavior = behavior.clone();
        }
    }

}
