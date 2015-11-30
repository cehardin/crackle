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
package org.cracke.example;

import java.util.function.Function;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 *
 * @author Chad
 */
public class AdaptorBehavior implements Behavior {
    private final Address target;
    private final Function<Message, Message> converter;

    public AdaptorBehavior(Address target, Function<Message, Message> converter) {
        this.target = target;
        this.converter = converter;
    }

    @Override
    public void process(Context context) {
        context.send(target, converter.apply(context.getMessage()));
    }
    
    
}