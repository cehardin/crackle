/**
 * This file is part of crackle-impl-local.
 *
 * crackle-impl-local is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-impl-local is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-impl-local.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.crackle.impl.local;

import org.crackle.Address;

/**
 * Creates addresses suitable for use within a single VM. They are unforgeable
 * due to the protections offered by the Java VM. However, they are not globally
 * unique and are not suitable for distributed processing.
 *
 * @author Chad Hardin
 */
final class LocalAddressFactory {

  /**
   * Creates a new address.
   * @return A new address, never null
   */
  public Address createAddress() {
    return new LocalAddress();
  }

  /**
   * A local address.
   */
  private static final class LocalAddress implements Address {

  }

}
