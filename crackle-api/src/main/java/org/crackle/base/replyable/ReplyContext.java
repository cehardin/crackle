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

import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 * An actor context that can also send replyable messaged.
 * @author Chad
 */
public class ReplyContext implements Context {
    private final Context context;
    private final ReplyController controller;

    public ReplyContext(Context context, ReplyController controller) {
        this.context = context;
        this.controller = controller;
    }

    @Override
    public Address getAddress() {
        return context.getAddress();
    }

    @Override
    public Message getMessage() {
        return context.getMessage();
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
    
    /**
     * Send a message where a reply is expected, along with the behavior
     * for processing that reply.
     * @param address The address of the actor to send the message to
     * @param message The message to send to the actor
     * @param replyBehavior The behavior to process the reply to this message
     */
    public void send(Address address, Message message, Behavior replyBehavior) {
        controller.send(context, address, message, replyBehavior);
    }
}
