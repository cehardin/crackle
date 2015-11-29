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
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 * A controller for handling replies.
 * @author Chad
 */
public interface ReplyController extends Serializable, Cloneable {
    
    /**
     * Set up the behavior for a reply.
     * @param context The actor context
     * @param to The address to send to.
     * @param message The message to send.
     * @param replyBehavior  The behavior for processing the reply
     */
    void send(Context context, Address to, Message message, Behavior replyBehavior);
    
    /**
     * Process a reply
     * @param context The actor context
     * @param replyMessage The reply message to process
     * @return true if a repy behavior was found and the reply processed
     */
    boolean process(Context context, ReplyMessage replyMessage);
    
    ReplyController clone();
}
