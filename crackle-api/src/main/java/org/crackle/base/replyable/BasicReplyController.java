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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 * A basic reply controller.
 * @author Chad
 */
public class BasicReplyController implements ReplyController {
    private final Map<MessageId, Behavior> replyBehaviors = new HashMap<>();

    @Override
    public void send(Context context, Address to, Message message, Behavior replyBehavior) {
        final ReplyableMessage replyableMessage = new ReplyableMessage(context.getAddress(), message);
        replyBehaviors.put(replyableMessage.getReplyWith(), Objects.requireNonNull(replyBehavior));
        context.send(to, replyableMessage);
    }

    @Override
    public boolean process(Context context, ReplyMessage replyMessage) {
        final Optional<Behavior> behavior = Optional.ofNullable(replyBehaviors.remove(replyMessage.getInReplyTo()));
        
        if(behavior.isPresent()) {
            behavior.get().process(Objects.requireNonNull(context));
        }
        
        return behavior.isPresent();
    }

    @Override
    public ReplyController clone() {
        final BasicReplyController clone = new BasicReplyController();
        
        for(final Map.Entry<MessageId, Behavior> entry : replyBehaviors.entrySet()) {
            clone.replyBehaviors.put(entry.getKey(), entry.getValue());
        }
        
        return clone;
    }
}
