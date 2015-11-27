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

import org.crackle.Address;

/**
 * Creates addresses suitable for use within a single VM.  They are unforgeable
 * due to the protections offered by the Java VM.  However, they are not globally
 * unique and are not suitable for distributed processing.
 * @author Chad
 */
public class LocalVmAddressFactory implements AddressFactory {

    @Override
    public Address createAddress() {
        return new LocalVmAddress();
    }

    private static final class LocalVmAddress implements Address {

    }

}
