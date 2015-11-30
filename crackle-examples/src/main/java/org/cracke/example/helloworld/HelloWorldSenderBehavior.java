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

import org.crackle.Address;
import org.crackle.base.StringMessage;
import org.crackle.base.replyable.AbstractReplyContextBehavior;
import org.crackle.base.replyable.ReplyContext;
import org.crackle.base.replyable.ReplyController;

/**
 *
 * @author Chad
 */
public class HelloWorldSenderBehavior extends AbstractReplyContextBehavior {
    private final Address target;

    public HelloWorldSenderBehavior(Address target, ReplyController replyController) {
        super(replyController);
        this.target = target;
    }
    
    @Override
    protected void process(ReplyContext context) {
        final StringMessage message = context.getMessageAs();
        
        context.send(
                target, 
                message, 
                (c) -> System.out.println(c.getMessageAs(StringMessage.class).getValue()));
    }
    
}
