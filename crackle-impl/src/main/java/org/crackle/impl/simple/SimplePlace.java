package org.crackle.impl.simple;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.crackle.Actor;
import org.crackle.Address;
import org.crackle.Message;
import org.crackle.Place;

/**
 *
 * @author Chad
 */
public class SimplePlace implements Place {
    private final BlockingQueue<Address> queue;
    private final ConcurrentMap<Address, ActorRecord> actors;
    private final Thread thread;
    
    public SimplePlace() {
        
        actors = new ConcurrentHashMap<>();
        queue = new LinkedBlockingQueue<>();
        thread = new Thread(new SimplePlaceRunner(this));
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
    
    

    BlockingQueue<Address> getQueue() {
        return queue;
    }

    ConcurrentMap<Address, ActorRecord> getActors() {
        return actors;
    }
    
    
    
    
    @Override
    public Address create(Class<? extends Actor> type) {
        final Address address = new SimpleAddress();
        final Actor actor = newActor(type);
        final ActorRecord record = new ActorRecord(actor, new ConcurrentLinkedQueue<>());
        
        actors.put(address, record);
        return address;
    }

    @Override
    public void send(Address address, Message message) {
        final Optional<ActorRecord> record = Optional.ofNullable(actors.get(address));
        if(record.isPresent()) {
            final Queue<Message> messages = record.get().getMessages();
            messages.offer(message.clone());
            queue.offer(address);
        }
    }
    
    private Actor newActor(Class<? extends Actor> type) {
        try {
            return type.newInstance();
        }
        catch(InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(String.format("Could not create actor of type %s", type), e);
        }
    }
    
}
