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

import java.util.EventListener;

/**
 * Listens for events from an {@link Engine}
 * @author Chad
 */
public interface EngineListener extends EventListener {

    /**
     * Called when an engine creates an actor.
     * @param event The engine event, never null.
     */
    void actorCreated(EngineEvent event);
    
    void messageUndeliverable(EngineEvent event);
}
