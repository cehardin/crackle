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

/**
 * Sends messages to actors.
 *
 * @author Chad Hardin
 */
@FunctionalInterface
public interface Sender {

  /**
   * Send a message to an actor.
   *
   * @param address The address of the actor
   * @param message The message to send
   * @throws NullPointerException If address or message are null
   */
  void send(Address address, Message message) throws NullPointerException;
}
