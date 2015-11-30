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
package org.cracke.example.helloworld;

import java.util.Optional;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.base.StringMessage;
import org.crackle.base.replyable.AbstractReplyingBehavior;

/**
 *
 * @author Chad
 */
public class HelloWorldReceiverBehavior extends AbstractReplyingBehavior {

    @Override
    protected Optional<Message> processThenReply(Context context) {
        final StringMessage message = context.getMessageAs();
        final String name = message.getValue();
        
        return Optional.of(new StringMessage(String.format("Hello, %s", name)));
    }
    
}
