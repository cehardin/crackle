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

import java.util.Objects;
import org.crackle.Address;
import org.crackle.Message;

/**
 * A message that can be replied to with a {@link ReplyableMessage}.
 * @author Chad
 */
public class ReplyableMessage implements Message {
    private final MessageId replyWith;
    private final Address replyTo;
    private final Message message;
    
    public ReplyableMessage(Address replyTo, Message message) {
        this.replyWith = new MessageId();
        this.replyTo = Objects.requireNonNull(replyTo);
        this.message = message.clone();
    }

    /**
     * Get the message id to reply with.
     * @return The message id to reply with
     */
    public MessageId getReplyWith() {
        return replyWith;
    }

    /**
     * Get the address to reply to.
     * @return The address to reply to
     */
    public Address getReplyTo() {
        return replyTo;
    }

    /**
     * Get the message
     * @return The message
     */
    public Message getMessage() {
        return message.clone();
    }

    @Override
    public Message clone() {
        return this;
    }
    
}
