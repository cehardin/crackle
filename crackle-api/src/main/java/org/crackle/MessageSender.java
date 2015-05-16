package org.crackle;

/**
 * Sends a message to an actor
 * @author Chad Hardin
 */
public interface MessageSender {

    /**
     * Send a message to an actor
     * @param <M> The message type
     * @param address The address of the actor
     * @param message The message to send
     */
    <M extends Message> void send(Address<M> address, M message);
}
