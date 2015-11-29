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
import org.crackle.Message;

/**
 * A reply message in response to a {@link ReplyMessage}
 * @author Chad
 */
public class ReplyMessage implements Message {
    private final MessageId inReplyTo;
    private final Message message;
    
    public ReplyMessage(MessageId inReplyTo, Message message) {
        this.inReplyTo = Objects.requireNonNull(inReplyTo);
        this.message = message.clone();
    }

    /**
     * Get the message message id this message in a reply for.
     * @return The message message id this message in a reply for.
     */
    public MessageId getInReplyTo() {
        return inReplyTo;
    }

    /**
     * Get the message.
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
