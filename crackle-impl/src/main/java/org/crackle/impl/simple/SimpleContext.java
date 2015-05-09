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
    private final Address address;

    public SimpleContext(SimplePlace place, Address address) {
        this.place = place;
        this.address = address;
    }

    @Override
    public void terminate() throws IllegalStateException {
        place.getPendingTermination().add(address);
    }

    @Override
    public void suspend() throws IllegalStateException {
        //do nothing for the simple impl
    }

    @Override
    public Address create(Class<? extends Actor> type) {
        return place.create(type);
    }

    @Override
    public void send(Address address, Message message) {
        place.send(address, message);
    }
}
