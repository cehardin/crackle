/**
 * This file is part of crackle-api.
 *
 * crackle-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-api.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.crackle;

import java.io.Serializable;
import java.util.Objects;

/**
 * The immutable behavior for an actor. An actor may potential have many different
 * behaviors over the course of its life. Behaviors MUST be immutable and should never
 * share any state with other behaviors.
 * @author Chad Hardin
 */
@FunctionalInterface
public interface Behavior extends Serializable {
    
    /**
     * Process a message.  An actor may create other actors, send messages,
     * and optionally change its behavior for the next message.
     * @param context The context to process under, never null
     */
    void process(Context context);

    default Behavior andThen(Behavior after) {
        Objects.requireNonNull(after);
        
        return (c) -> {
            process(c);
            after.process(c);
        };
    }
}
