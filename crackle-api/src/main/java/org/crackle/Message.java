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
 * A message is sent to and processed by an actor.
 *
 * @author Chad Hardin
 */
public interface Message extends Serializable, Cloneable {

  /**
   * Clone the message. Messages must be able to clone themselves. If the
   * message is immutable, it may return itself.
   *
   * @return A clone of the message, never null.
   */
  Message clone();
}
