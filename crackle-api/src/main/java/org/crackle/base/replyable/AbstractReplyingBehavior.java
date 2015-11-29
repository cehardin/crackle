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

import java.util.Optional;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 * A behavior that can receive messages that request a reply and then
 * reply to them.
 * @author Chad
 */
public abstract class AbstractReplyingBehavior implements Behavior {
    
    @Override
    public final void process(final Context context) {
        final ReplyableMessage replyableMessage = ReplyableMessage.class.cast(context.getMessage());
        final Optional<Message> reply = processThenReply(new Context() {
            @Override
            public Address getAddress() {
                return context.getAddress();
            }

            @Override
            public Message getMessage() {
                return replyableMessage.getMessage();
            }

            @Override
            public void change(Behavior behavior) throws IllegalStateException, NullPointerException {
                context.change(behavior);
            }

            @Override
            public Address create(Behavior behavior) throws NullPointerException {
                return context.create(behavior);
            }

            @Override
            public void send(Address address, Message message) throws NullPointerException {
                context.send(address, message);
            }
        });
        
        if(reply.isPresent()) {
            context.send(replyableMessage.getReplyTo(), new ReplyMessage(replyableMessage.getReplyWith(), reply.get()));
        }
    };
    
    /**
     * Process the message and optional reply
     * @param context The context
     * @return An optional reply
     */
    protected abstract Optional<Message> processThenReply(Context context);
    
}
