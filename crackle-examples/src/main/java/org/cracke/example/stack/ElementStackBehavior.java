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

import java.util.Optional;
import org.crackle.Address;
import org.crackle.Context;

/**
 *
 * @author Chad
 */
class ElementStackBehavior extends AbstractStackBehavior {
    private final AbstractStackBehavior previousBehavior;
    private final Address value;

    public ElementStackBehavior(AbstractStackBehavior previousBehavior, Address value) {
        this.previousBehavior = previousBehavior;
        this.value = value;
    }
    
    @Override
    protected void push(Context context, Address value) {
        context.change(new ElementStackBehavior(this, value));
    }

    @Override
    protected Optional<Address> pop(Context context) {
        context.change(previousBehavior);
        return Optional.of(value);
    }
    
    
}
