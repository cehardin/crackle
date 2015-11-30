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
package org.cracke.example.stack;

import java.util.Optional;
import org.crackle.Address;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.base.replyable.AbstractReplyingBehavior;

/**
 *
 * @author Chad
 */
abstract class AbstractStackBehavior extends AbstractReplyingBehavior {

    @Override
    public Optional<Message> processThenReply(Context context) {
        
        final Optional<Message> reply;
        
        if(PushMessage.class.isInstance(context.getMessage())) {
            final PushMessage message = context.getMessageAs();
            push(context, message.getValue());
            reply = Optional.of(new PushReplyMessage());
            
        } else if(PopMessage.class.isInstance(context.getMessage())) {
            reply = Optional.of(new PopReplyMessage(pop(context)));
            
        }
        else {
            reply = Optional.empty();
        }
        
        return reply;
    }
    
    protected abstract void push(Context context, Address value);
    
    protected abstract Optional<Address> pop(Context context);
    
}
