package org.crackle;

/**
 * Sends a message to an address
 * @author Chad
 */
public interface Sender {
    /**
     * Send a message to an actor at the specified address
     * @param recipient The address of the recipient actor.
     * @param message The message to send
     */
    void send(Address recipient, Message message);
}
