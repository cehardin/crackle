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
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;

/**
 * A task which represents a message to be processed by an actor.
 * @author Chad Hardin
 */
final class LocalTask implements Runnable {

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
   * Create a local task.
   * @param e The engine
   * @param a The address
   * @param m The message
   */
  LocalTask(
          final LocalEngine e,
          final Address a,
          final Message m) {
    this.engine = Objects.requireNonNull(e, "Engine was null");
    this.address = Objects.requireNonNull(a, "Address was null");
    this.message = Objects.requireNonNull(m, "Message was null").clone();
  }

  /**
   * Looks up the actor and processes the message. If the actor is locked, the
   * message will not be processed until a later time.
   */
  @Override
  public void run() {
    final Optional<BehaviorLock> behaviorLock =
            engine.findBehaviorLock(address);

    if (behaviorLock.isPresent()) {
      final Lock lock = behaviorLock.get().getLock();

      if (lock.tryLock()) {
        try {
          final Behavior behavior = behaviorLock.get().getBehavior();
          final LocalContext context =
                  new LocalContext(engine, address, message);
          behavior.process(context);
          if (!context.isBehaviorChanged()) {
            throw new IllegalStateException(
                    String.format(
                            "Agent %s did not change behavior",
                            address));
          }
        } finally {
          lock.unlock();
        }
      } else {
        engine.send(address, message);
      }
    }
  }
}
