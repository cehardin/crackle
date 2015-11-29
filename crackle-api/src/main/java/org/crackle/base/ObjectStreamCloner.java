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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Function;

/**
 * Clones a serializable object by using object output and input streams
 * @author Chad
 */
final class ObjectStreamCloner implements Function<Object, Object> {
    public static final ObjectStreamCloner INSTANCE = new ObjectStreamCloner();
    
    private ObjectStreamCloner() {
        super();
    }
    
    @Override
    public Object apply(final Object original) {
        return fromBytes(toBytes(original));
    }

    private byte[] toBytes(final Object object) {
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (final ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeUnshared(object);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Could not convert to bytes", e);
        }
    }

    private Object fromBytes(final byte[] bytes) {
        try (final ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            try (final ObjectInputStream ois = new ObjectInputStream(bais)) {
                return ois.readUnshared();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not convert from bytes", e);
        }
    }
}
