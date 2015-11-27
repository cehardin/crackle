package org.crackle.impl.simple;

import java.util.Optional;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Context;
import org.crackle.Message;
import org.crackle.Place;

/**
 *
 * @author Chad
 */
class SimpleActor {

    private Behavior behavior;
    private Optional<Behavior> nextBehavior;

    public SimpleActor(Behavior behavior) {
        this.behavior = behavior;
        this.nextBehavior = Optional.empty();
    }

    public synchronized void process(Address address, Place place, Message message) {
        
        behavior.process(new Context() {
            @Override
            public Address getAddress() {
                return address;
            }

            @Override
            public void change(Behavior behavior) throws IllegalStateException, NullPointerException {
                if(nextBehavior.isPresent()) {
                    throw new IllegalStateException();
                }
                else {
                    nextBehavior = Optional.of(behavior.clone());
                }
            }

            @Override
            public Place getPlace() {
                return place;
            }
        });
        
        if(nextBehavior.isPresent()) {
            behavior = nextBehavior.get();
            nextBehavior = Optional.empty();
        }
    }

}
