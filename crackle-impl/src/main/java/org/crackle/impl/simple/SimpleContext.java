package org.crackle.impl.simple;

import org.crackle.Actor;
import org.crackle.Address;
import org.crackle.Context;
import org.crackle.Message;

/**
 *
 * @author Chad
 */
public class SimpleContext implements Context {
    private final SimplePlace place;
    private final Address<?> address;

    /**
     * 
     * @param place
     * @param address 
     */
    public SimpleContext(SimplePlace place, Address<?> address) {
        this.place = place;
        this.address = address;
    }

    /**
     * 
     * @throws IllegalStateException 
     */
    @Override
    public void terminate() throws IllegalStateException {
        place.getPendingTermination().add(address);
    }

    /**
     * 
     * @throws IllegalStateException 
     */
    @Override
    public void suspend() throws IllegalStateException {
        //do nothing for the simple impl
    }

    /**
     * 
     * @param <M>
     * @param <A>
     * @param type
     * @return 
     */
    @Override
    public <M extends Message, A extends Actor<M>> Address<M> create(Class<A> type) {
        return place.create(type);
    }

    /**
     * 
     * @param <M>
     * @param address
     * @param message 
     */
    @Override
    public <M extends Message> void send(Address<M> address, M message) {
        place.send(address, message);
    }
}
