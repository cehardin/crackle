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
    private final ConcurrentMap<Address<?>, ActorRecord> actorRecords;
    private final Set<Address<?>> pendingTermination;
    private final Thread thread;
    
    /**
     * 
     */
    public SimplePlace() {
        actorRecords = new ConcurrentHashMap<>();
        queue = new LinkedBlockingQueue<>();
        thread = new Thread(new SimplePlaceRunner(this));
        pendingTermination = new ConcurrentSkipListSet<>();
    }

    /**
     * 
     */
    @Override
    public void start() {
        thread.start();
    }

    /**
     * 
     */
    @Override
    public void stop() {
        thread.interrupt();
    }

    /**
     * 
     * @return 
     */
    @Override
    public Set<Address<?>> getActors() {
        return Collections.unmodifiableSet(getActorRecords().keySet());
    }
    

    /**
     * 
     * @return 
     */
    BlockingQueue<ActionRecord> getQueue() {
        return queue;
    }

    /**
     * 
     * @return 
     */
    ConcurrentMap<Address<?>, ActorRecord> getActorRecords() {
        return actorRecords;
    }

    /**
     * 
     * @return 
     */
    Set<Address<?>> getPendingTermination() {
        return pendingTermination;
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
        final Address address = new SimpleAddress();
        final Actor actor = newActor(type);
        final SimpleContext context = new SimpleContext(this, address);
        final ActorRecord record = new ActorRecord(actor, new ConcurrentLinkedQueue<>(), context);
        
        actorRecords.put(address, record);
        return address;
    }

    /**
     * 
     * @param <M>
     * @param address
     * @param message 
     */
    @Override
    public <M extends Message> void send(Address<M> address, M message) {
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
    
    /**
     * 
     * @param type
     * @return 
     */
    private Actor newActor(Class<? extends Actor> type) {
        try {
            return type.newInstance();
        }
        catch(InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(String.format("Could not create actor of type %s", type), e);
        }
    }
    
}
