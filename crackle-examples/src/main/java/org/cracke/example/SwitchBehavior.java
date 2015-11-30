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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;

/**
 *
 * @author Chad
 */
public class SwitchBehavior implements Behavior {
    private final SwitchStrategy strategy;
    private final Set<Address> addresses;

    public SwitchBehavior(SwitchStrategy strategy, Collection<Address> addresses) {
        this.strategy = Objects.requireNonNull(strategy);
        this.addresses = new HashSet<>(addresses);
    }

    @Override
    public final void process(Context context) {
        final Optional<Address> address = strategy.select(context, addresses);
        
        if(address.isPresent()) {
            context.send(address.get(), context.getMessage());
        }
    }
    
}
