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

import org.crackle.Behavior;
import org.crackle.Context;

/**
 * A behavior that uses a reply controller and provides a reply context
 * for sub-classes.
 * @author Chad
 */
public abstract class AbstractReplyContextBehavior implements Behavior {
    private final ReplyController replyController;

    public AbstractReplyContextBehavior(ReplyController replyController) {
        this.replyController = replyController.clone();
    }

    @Override
    public final void process(Context context) {
        process(new ReplyContext(context, replyController));
    }
    
    protected abstract void process(ReplyContext context);
}
