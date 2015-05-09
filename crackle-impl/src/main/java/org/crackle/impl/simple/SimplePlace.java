package org.crackle.impl.simple;

import java.util.Collections;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
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
    private final BlockingQueue<ActionRecord> queue;
    private final ConcurrentMap<Address, ActorRecord> actorRecords;
    private final Set<Address> pendingTermination;
    private final Thread thread;
    
    public SimplePlace() {
        
        actorRecords = new ConcurrentHashMap<>();
        queue = new LinkedBlockingQueue<>();
        thread = new Thread(new SimplePlaceRunner(this));
        pendingTermination = new ConcurrentSkipListSet<>();
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public Set<Address> getActors() {
        return Collections.unmodifiableSet(getActorRecords().keySet());
    }
    

    BlockingQueue<ActionRecord> getQueue() {
        return queue;
    }

    ConcurrentMap<Address, ActorRecord> getActorRecords() {
        return actorRecords;
    }

    Set<Address> getPendingTermination() {
        return pendingTermination;
    }
    
    @Override
    public Address create(Class<? extends Actor> type) {
        final Address address = new SimpleAddress();
        final Actor actor = newActor(type);
        final SimpleContext context = new SimpleContext(this, address);
        final ActorRecord record = new ActorRecord(actor, new ConcurrentLinkedQueue<>(), context);
        
        actorRecords.put(address, record);
        return address;
    }

    @Override
    public void send(Address address, Message message) {
        final Optional<ActorRecord> record = Optional.ofNullable(actorRecords.get(address));
        if(record.isPresent()) {
            final Queue<Message> messages = record.get().getMessages();
            messages.offer(message.clone());
            try {
                queue.put(new ActionRecord(ActionRecord.Type.Message, address));
            }
            catch(InterruptedException e) {
                throw new RuntimeException(String.format("Failed to send message to %s : %s", address, message), e);
            }
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
