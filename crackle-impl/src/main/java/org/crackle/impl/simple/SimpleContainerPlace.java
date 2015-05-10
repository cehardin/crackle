package org.crackle.impl.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.crackle.Actor;
import org.crackle.Address;
import org.crackle.Message;
import org.crackle.Place;

/**
 *
 * @author Chad
 */
public class SimpleContainerPlace implements Place {
    private final List<SimplePlace> places;
    private final AtomicInteger nextPlaceIndex = new AtomicInteger(0);
    
    public SimpleContainerPlace(final int numberOfPlaces) {
        places = new ArrayList<>(numberOfPlaces);
        
        for(int i=0; i < numberOfPlaces; i++) {
            final SimplePlace place = new SimplePlace();
            places.add(place);
        }
    }

    @Override
    public void start() {
        places.parallelStream().forEach(p -> p.start());
    }

    @Override
    public void stop() {
        places.parallelStream().forEach(p -> p.stop());
    }

    @Override
    public Iterable<Place> getSubPlaces() {
        return Collections.unmodifiableList(places);
    }

    @Override
    public Address create(Class<? extends Actor> type) {
        final int placeIndex = nextPlaceIndex.getAndUpdate(n -> (n + 1) % (places.size() - 1));
        
        
        return places.get(placeIndex).create(type);
    }

    @Override
    public void send(Address address, Message message) {
        places.parallelStream().forEach(p -> p.send(address, message));
    }

    @Override
    public Set<Address> getActors() {
        final Set<Address> actors = new HashSet<>();
        
        for(final Place place : places) {
            actors.addAll(place.getActors());
        }
        
        return Collections.unmodifiableSet(actors);
    }
    
    
}
