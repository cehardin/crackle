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

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Engine;
import org.crackle.Message;

/**
 * An engine runs actors.
 *
 * @author Chad Hardin
 */
public final class LocalEngine implements Engine {

  /**
   * The executor.
   */
  private final Executor executor;

  /**
   * The address factory.
   */
  private final LocalAddressFactory addressFactory = new LocalAddressFactory();

  /**
   * The behavior locks.
   */
  private final Map<Address, BehaviorLock> behaviorLocks;

  /**
   * Create the engine.
   * @param e The executor to use
   */
  public LocalEngine(final Executor e) {
    this.executor = Objects.requireNonNull(e, "Executor was null");
    this.behaviorLocks = Collections.synchronizedMap(
            new WeakHashMap<Address, BehaviorLock>());
  }

  /**
   * Creates an actor and returns its address.
   *
   * @param behavior The initial behavior of the actor
   * @return The address of the newly created actor, never null
   * @throws NullPointerException If behavior is null
   */
  @Override
  public Address create(final Behavior behavior) {
    final Address address = addressFactory.createAddress();
    final BehaviorLock behaviorLock = new BehaviorLock(behavior);

    if (behaviorLocks.putIfAbsent(address, behaviorLock) != null) {
      throw new IllegalStateException(
              String.format(
                 "Same address was used more than once: %s",
                 address));
    }
    return address;
  }

  /**
   * Send a message to an actor.
   *
   * @param address The address of the actor
   * @param message The message to send
   * @throws NullPointerException If address or message are null
   */
  @Override
  public void send(final Address address, final Message message) {
    executor.execute(new LocalTask(this, address, message));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Address> getAddresses() {
    synchronized (behaviorLocks) {
      return Collections.unmodifiableSet(new HashSet<>(behaviorLocks.keySet()));
    }
  }

  /**
   * Find a behavior lock.
   * @param address The address
   * @return The behavior lock
   */
  Optional<BehaviorLock> findBehaviorLock(final Address address) {
    return Optional.ofNullable(behaviorLocks.get(address));
  }

  /**
   * Replace a behavior.
   * @param address The address
   * @param behavior  The behavior
   */
  void replaceBehavior(final Address address, final Behavior behavior) {
    final Behavior previousBehavior = findBehaviorLock(address)
            .orElseThrow(
                    () -> new IllegalStateException(
                            String.format(
                                    "Could not find : %s",
                                    address)))
            .getBehavior();

    if (previousBehavior.equals(behavior)) {
      throw new IllegalStateException(
              String.format(
                  "Cannot change behavior of %s to the previous behavior : %s",
                      address,
                      behavior));
    } else {
      behaviorLocks.put(address, new BehaviorLock(behavior));
    }
  }
}
