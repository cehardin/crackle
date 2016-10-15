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

/**
 * The immutable behavior for an actor. An actor may potential have many
 * different behaviors over the course of its life. Behaviors MUST be immutable
 * and should never share any state with other behaviors.
 *
 * @author Chad Hardin
 * @param <M> The type of message
 */
@FunctionalInterface
public interface Behavior<M extends Message> extends Serializable {

  /**
   * Process a message. An actor may create other actors and send messages, The
   * actor must change to a different behavior object each time this method is
   * called.
   *
   * @param context The context to process under, never null
   */
  void process(Context<M> context);
}
