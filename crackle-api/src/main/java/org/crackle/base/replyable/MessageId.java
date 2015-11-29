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
package org.crackle.base.replyable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Uniquely Identifies a message.
 * @author Chad
 */
public final class MessageId implements Serializable {
    private final UUID uuid;
    
    public MessageId() {
        uuid = UUID.randomUUID();
    }
    
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        final boolean equal;
        
        if(this == o) {
            equal = true;
        }
        else if(getClass().isInstance(o)) {
            final MessageId other = getClass().cast(o);
            equal = uuid.equals(other.uuid);
        }
        else {
            equal = false;
        }
        
        return equal;
    }
}
