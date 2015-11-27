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
package org.crackle.impl;

import java.util.EventObject;
import java.util.Objects;
import java.util.Optional;
import org.crackle.Address;
import org.crackle.Message;

/**
 * Engine events that passed to instance of {@link EngineListener}.
 * @author Chad
 */
public class EngineEvent extends EventObject {
    private final Engine engine;
    private final Address address;
    private final Optional<Message> message;

    public EngineEvent(Engine engine, Address address, Optional<Message> message) {
        super(engine);
        this.engine = Objects.requireNonNull(engine);
        this.address = Objects.requireNonNull(address);
        this.message = Objects.requireNonNull(message);
    }

    /**
     * Get the engine.
     * @return  The engine, never null.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Get the address
     * @return The address, never null
     */
    public Address getAddress() {
        return address;
    }

    public Optional<Message> getMessage() {
        return message;
    }
}
