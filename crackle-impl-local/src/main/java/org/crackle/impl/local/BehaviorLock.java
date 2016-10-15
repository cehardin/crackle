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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.crackle.Behavior;

/**
 * A combination of a behavior and a lock.
 */
final class BehaviorLock {

  /**
   * The lock.
   */
  private final Lock lock = new ReentrantLock();

  /**
   * The behavior.
   */
  private final Behavior behavior;

  /**
   * Construct with a b.
   * @param b The b
   * @throws NullPointerException If b is null
   */
  BehaviorLock(final Behavior b) {
    this.behavior = Objects.requireNonNull(b, "behavior was null");
  }

  /**
   * Get the lock.
   * @return The lock, never null
   */
  public Lock getLock() {
    return lock;
  }

  /**
   * Get the behavior.
   * @return The behavior, never null
   */
  public Behavior getBehavior() {
    return behavior;
  }
}
