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
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import org.crackle.Message;

/**
 * A message that contains a single serializable object. The object must either
 * implement {@link Cloneable} or {@link Serializable}. If the object implements
 * {@link Cloneable} it will be cloned. Otherwise, if the object implements
 * {@link Serializable}, it will be serialized and deserialized to make a copy.
 *
 * @param <T> The type of object
 * @author Chad Hardin
 */
public final class ObjectMessage<T> implements Message {

  /**
   * Create a clone either by using {@link Cloneable} or {@link Serializable}.
   *
   * @param <T> The type of object.
   * @param o The object to clone
   * @return A clone of the object
   * @throws NullPointerException If the object is null
   * @throws IllegalArgumentException If the object is neither cloneable or
   * serializable
   */
  private static <T> T clone(final T o)
          throws NullPointerException, IllegalArgumentException {
    final Class<?> type = Objects.requireNonNull(o, "Object was null")
            .getClass();
    final T c;

    if (Cloneable.class.isInstance(o)) {
      try {
        final Method cloneMethod = type.getMethod("clone");
        c = (T) cloneMethod.invoke(o);
      } catch (NoSuchMethodException
              | IllegalAccessException
              | InvocationTargetException e) {
        throw new IllegalArgumentException(
                String.format(
                        "Could not clone object of type %s : %s",
                        type,
                        o),
                e);
      }
    } else if (Serializable.class.isInstance(o)) {
      final byte[] bytes;

      try {
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
          try (final ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(o);
          }
          baos.flush();
          bytes = baos.toByteArray();
        }

        try (final ByteArrayInputStream bais =
                new ByteArrayInputStream(bytes)) {
          try (final ObjectInputStream ois = new ObjectInputStream(bais)) {
            c = (T) ois.readObject();
          }
        }
      } catch (IOException | ClassNotFoundException e) {
        throw new IllegalArgumentException(
                String.format(
                        "Could not clone object of type %s : %s",
                        type,
                        o),
                e);
      }
    } else {
      throw new IllegalArgumentException(
              String.format(
                      "Could not clone object of type %s : %s",
                      type,
                      o));
    }

    return c;
  }

  /**
   * The object of the message.
   */
  private final T object;

  /**
   * Construct with a non-null o that either impllements {@link Cloneable}
   * or {@link Serializable}.
   *
   * @param o The o
   * @throws NullPointerException If the o is null
   * @throws IllegalArgumentException If the o is neither cloneable or
 serializable
   */
  private ObjectMessage(final T o)
          throws NullPointerException, IllegalArgumentException {
    this.object = clone(o);
  }

  /**
   * Get a copy of the object.
   *
   * @return A copy of the object, never null.
   */
  public T getObject() {
    return clone(object);
  }

  /**
   * Get the object, casted as a subclass.
   *
   * @param <K> The type of the object
   * @return The typed object. never null
   * @throws ClassCastException If th object cannot be casted to that type
   */
  public <K extends T> K getObjectAs() throws ClassCastException {
    return (K) getObject();
  }

  /**
   * Get the object, casted as a subclass.
   *
   * @param <K> The type of the object
   * @param type The class to cast to, must not be null.
   * @return The typed object. never null
   * @throws NullPointerException If the type is null.
   * @throws ClassCastException If th object cannot be casted to that type
   */
  public <K extends T> K getObjectAs(final Class<K> type)
          throws NullPointerException, ClassCastException {
    return Objects.requireNonNull(type, "Type was null").cast(getObject());
  }

  /**
   * Clone the message.
   *
   * @return The clone, never null
   */
  @Override
  public ObjectMessage<T> clone() {
    return new ObjectMessage(object);
  }
}
