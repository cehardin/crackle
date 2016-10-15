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

import java.util.Objects;

/**
 * Given to a {@link Behavior} when it processes a {@link Message}.
 *
 * @author Chad Hardin
 * @param <M> The type of message
 * @see Behavior#process(org.crackle.Context)
 */
public interface Context<M extends Message> extends Creator, Sender {

  /**
   * Get the address for the actor being processed.
   *
   * @return The address of the actor being processed, never null.
   */
  Address getAddress();

  /**
   * Get the message.
   *
   * @return The message, never null.
   */
  M getMessage();

  /**
   * Get the message, casted to a different type.
   *
   * @param <T> The message type to cast to
   * @return The messaged, casted to the new type
   * @throws ClassCastException If the message cannot be casted.
   */
  default <T extends M> T getMessageAs() throws ClassCastException {
    return (T) getMessage();
  }

  /**
   * Get the message, casted to a different type.
   *
   * @param <T> The message type to cast to
   * @param t The class of the message type to cast to, must not be null
   * @return The messaged, casted to the new type
   * @throws ClassCastException If the message cannot be casted.
   * @throws NullPointerException If t is null
   */
  default <T extends M> T getMessageAs(final Class<T> t)
          throws ClassCastException, NullPointerException {
    return Objects.requireNonNull(t, "Class was null").cast(getMessage());
  }

  /**
   * Change the behavior of the actor for the next message to be processed. An
   * actor must change its behavior while processing a message. However, it can
   * only change it once and the behavior must be a different object instance
   * thsn the current one.
   *
   * @param behavior The behavior to use for the next message processed.
   * @throws IllegalStateException If the behavior is changed more than once.
   * @throws NullPointerException If the behavior is null
   */
  void change(Behavior behavior)
          throws IllegalStateException, NullPointerException;
}
