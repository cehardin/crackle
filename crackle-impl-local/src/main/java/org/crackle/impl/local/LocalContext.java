/**
 * This file is part of crackle-impl-local.
 *
 * crackle-impl-local is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * crackle-impl-local is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with crackle-impl-local.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.crackle.impl.local;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;

/**
 * A local context.
 *
 * @author Chad Hardin
 */
final class LocalContext implements Context {

  /**
   * The engine.
   */
  private final LocalEngine engine;

  /**
   * The address.
   */
  private final Address address;

  /**
   * The message.
   */
  private final Message message;

  /**
   * Indicates if the behavior has changed.
   */
  private final AtomicBoolean behaviorChanged = new AtomicBoolean(false);

  /**
   * Create a local context.
   *
   * @param e The engine
   * @param a The address
   * @param m The message
   */
  LocalContext(
          final LocalEngine e,
          final Address a,
          final Message m) {
    this.engine = Objects.requireNonNull(e, "Engine was null");
    this.address = Objects.requireNonNull(a, "Address was null");
    this.message = Objects.requireNonNull(m, "Message was null");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Address getAddress() {
    return address;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Message getMessage() {
    return message;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void change(final Behavior behavior)
          throws IllegalStateException, NullPointerException {
    if (behaviorChanged.compareAndSet(false, true)) {
      engine.replaceBehavior(address, behavior);
    } else {
      throw new IllegalStateException("Behavior has already been changed");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Address create(final Behavior behavior) throws NullPointerException {
    return engine.create(behavior);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void send(final Address a, final Message m)
          throws NullPointerException {
    engine.send(a, m);
  }

  /**
   * Determine if the behavior has changed.
   *
   * @return True if changed
   */
  public boolean isBehaviorChanged() {
    return behaviorChanged.get();
  }
}
