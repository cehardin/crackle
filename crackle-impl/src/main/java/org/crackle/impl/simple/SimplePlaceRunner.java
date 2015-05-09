package org.crackle.impl.simple;

import java.util.Optional;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crackle.Actor;
import org.crackle.Address;
import org.crackle.Message;

/**
 *
 * @author Chad
 */
public class SimplePlaceRunner implements Runnable {

    private final Logger logger = Logger.getGlobal();
    private final SimplePlace place;

    public SimplePlaceRunner(SimplePlace place) {
        this.place = place;
    }

    @Override
    public void run() {
        try {
            while (true) {
                process(place.getQueue().take());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

    private void process(final Address address) {
        final Optional<ActorRecord> record = Optional.ofNullable(place.getActors().get(address));

        if (record.isPresent()) {
            final Queue<Message> messages = record.get().getMessages();
            final Actor actor = record.get().getActor();

            while (true) {
                final Optional<Message> message = Optional.ofNullable(messages.poll());

                if (message.isPresent()) {
                    try {
                        actor.message(new SimpleContext(place, address), message.get());
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, String.format("Actor %s failed to process message: %s", address, message.get()), e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
