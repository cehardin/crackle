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
package org.crackle.base;

import org.crackle.Message;

/**
 * A message that contains a byte array
 * @author Chad
 */
public class ByteArrayMessage implements Message {
    private final byte[] bytes;
    
    public ByteArrayMessage(final byte[] bytes) {
        this.bytes = bytes.clone();
    }
    
    public byte[] getBytes() {
        return bytes.clone();
    }
    
    @Override
    public ByteArrayMessage clone() {
        return this;
    }
}
