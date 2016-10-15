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

import java.util.Set;

/**
 * A place where actors execute.
 *
 * @author Chad Hardin
 */
public interface Engine extends Creator, Sender {

  /**
   * Get all of the actor addresses known by this engine.
   *
   * @return an immutable set of actor addresses, never null.
   */
  Set<Address> getAddresses();
}
