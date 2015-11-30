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

import org.crackle.Address;
import org.crackle.Message;
import org.crackle.base.replyable.ReplyableMessage;

/**
 *
 * @author Chad
 */
public class PopMessage extends ReplyableMessage {
    private final Address value;

    public PopMessage(Address value, Address replyTo, Message message) {
        super(replyTo, message);
        this.value = value;
    }

    public Address getValue() {
        return value;
    }

    @Override
    public Message clone() {
        return this;
    }
}
