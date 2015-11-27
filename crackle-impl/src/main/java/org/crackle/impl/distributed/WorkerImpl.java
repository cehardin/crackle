package org.crackle.impl.distributed;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crackle.Address;
import org.crackle.Behavior;
import org.crackle.Message;
import org.crackle.impl.Engine;
import org.crackle.impl.EngineEvent;
import org.crackle.impl.EngineListener;

/**
 *
 * @author Chad
 */
public class WorkerImpl implements Worker, EngineListener {
    private static final Logger logger = Logger.getLogger(WorkerImpl.class.getName());
    private final List<WorkerListener> listeners = new ArrayList<>();
    private final Engine engine;

    public WorkerImpl(Engine engine) {
        this.engine = Objects.requireNonNull(engine);
    }
    
    public void start() {
        engine.addListener(this);
    }
    
    public void stop() {
        engine.addListener(this);
    }

    @Override
    public void addListener(WorkerListener listener) throws RemoteException {
        listeners.add(Objects.requireNonNull(listener));
    }

    @Override
    public void removeListener(WorkerListener listener) throws RemoteException {
        listeners.remove(Objects.requireNonNull(listener));
    }

    @Override
    public Address create(Behavior behavior) throws RemoteException {
        return engine.create(behavior);
    }

    @Override
    public boolean send(Address address, Message message) throws RemoteException {
        return engine.send(address, message);
    }

    @Override
    public boolean contains(Address address) throws RemoteException {
        return engine.contains(address);
    }

    @Override
    public void actorCreated(EngineEvent event) {
        for(final WorkerListener listener : listeners) {
            try {
                listener.actorCreated(this, event.getAddress());
            }
            catch(RemoteException e) {
                logger.log(Level.WARNING, "Could not propage actor created event", e);
            }
        }
    }

    @Override
    public void messageUndeliverable(EngineEvent event) {
        for(final WorkerListener listener : listeners) {
            try {
                listener.messageUndeliverable(this, event.getAddress(), event.getMessage().get());
            }
            catch(RemoteException e) {
                logger.log(Level.WARNING, "Could not propage message underliverable event", e);
            }
        }
    } 
}
