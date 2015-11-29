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

import java.util.function.Function;
import org.crackle.Message;

/**
 * A message that contains a single serializable object. 
 * @author Chad
 */
public class ObjectMessage implements Message {
    
    /**
     * Create a message of serializable object, which will be cloned via serializable.
     * @param object The object to put in the message
     * @return  The message
     */
    public static ObjectMessage mutableObject(final Object object) {
        return object(object, ObjectStreamCloner.INSTANCE);
    }
    
    /**
     * Create a message of a serializable immutable object.
     * @param object The object to put in the message
     * @return The message
     */
    public static ObjectMessage immutableObject(final Object object) {
        return object(object, ImmutableCloner.INSTANCE);
    }
    
    /**
     * Create a message of a serializable object, which is cloned by the provided function.
     * @param object The object to put in the message
     * @param cloner Creates clones of the object
     * @return The message
     */
    public static ObjectMessage object(final Object object, final Function<Object, Object> cloner) {
        return new ObjectMessage(object, cloner);
    }
    
    private final Object object;
    private final Function<Object, Object> cloner;
    
    private ObjectMessage(final Object object, final Function<Object, Object> cloner) {
        this.object = cloner.apply(object);
        this.cloner = cloner;
    }
    
    /**
     * Get the object.
     * @return The object
     */
    public Object getObject() {
        return cloner.apply(object);
    }
    
    /**
     * Get the typed object.
     * @param <T> The type of the object
     * @return The typed object
     */
    public <T> T getObjectAs() {
        return (T)getObject();
    }
    
    /**
     * Get the typed object.
     * @param <T> The type of the object
     * @param type The class of the type
     * @return The typed object
     */
    public <T> T getObjectAs(final Class<T> type) {
        return type.cast(getObject());
    }
    
    @Override
    public ObjectMessage clone() {
        final Object clone = object == null ? null : cloner.apply(object);
        return new ObjectMessage(clone, cloner);
    }
}
