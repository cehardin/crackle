/**
 * This file is part of crackle-examples.
 *
 * crackle-examples is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-examples is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-examples.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cracke.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.base.ObjectMessage;

/**
 *
 * @author Chad
 */
public class BufferBehavior implements Behavior {
    private final Address target;
    private final int capacity;
    private final List<Message> buffer;

    public BufferBehavior(final Address target, final int capacity) {
        this.target = target;
        this.capacity = capacity;
        this.buffer = new ArrayList<>(capacity);
    }

    @Override
    public void process(Context context) {
        
        buffer.add(context.getMessage());
        
        if(buffer.size() == capacity) {
            final List<Message> bufferCopy = Collections.unmodifiableList(new ArrayList<>(buffer));
            final Message message = ObjectMessage.immutableObject(bufferCopy);
            
            context.send(target, message);
            buffer.clear();
        }
        
    }
    
    
    
    
}
