/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.engine;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.crackle.Actor;
import org.crackle.ActorContext;
import org.crackle.ActorId;
import org.crackle.ActorType;

/**
 *
 * @author chad
 */
public class DefaultActorEngine implements ActorEngine {

    private ExecutorService executorService;
    private ActorIdFactory actorIdFactory;
    private ActorFactory actorFactory;
    private State currentState = State.Stopped;
    private State desiredState = State.Stopped;
    private Thread processThread;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ActorIdFactory getActorIdFactory() {
        return actorIdFactory;
    }

    public void setActorIdFactory(ActorIdFactory actorIdFactory) {
        this.actorIdFactory = actorIdFactory;
    }

    public ActorFactory getActorFactory() {
        return actorFactory;
    }

    public void setActorFactory(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    public void start() {
        switch (getState()) {
            case Stopped:
                desiredState = State.Running;
                currentState = State.Starting;
                processThread = new Thread(new Runnable() {
                    public void run() {
                        process();
                    }
                });
                break;
            case Stopping:
            case Starting:
            case Running:
                break;
        }
    }

    public void stop() {
        switch (getState()) {
            case Stopped:
            case Stopping:
                break;
            case Starting:
            case Running:
                desiredState = State.Stopped;
                currentState = State.Stopping;
                break;
        }
    }

    public State getState() {
        return currentState;
    }

    private void process() {
        final Set<MessageRecord> messageRecords = new ConcurrentSkipListSet<MessageRecord>();
        final ConcurrentMap<ActorId, Actor> actorsMap = new ConcurrentHashMap();
        final Set<ActorId> actorsInProcess = new ConcurrentSkipListSet();
        currentState = State.Running;


        while (desiredState.equals(State.Running)) {
            for (final MessageRecord messageRecord : messageRecords) {

                if (desiredState.equals(State.Stopped)) {
                    break;
                }
                
                messageRecords.remove(messageRecord);

                executorService.submit(new Runnable() {
                    public void run() {
                        final ActorId actorId = messageRecord.getActorId();
                        final Actor actor = actorsMap.get(actorId);
                        if (actor != null) {
                            if (actorsInProcess.add(actorId)) {
                                try {
                                    final AtomicBoolean terminate = new AtomicBoolean(false);
                                    final ActorContext actorContext = new DefaultActorContext(
                                            actorId, actorsMap, messageRecords,
                                            terminate, actorIdFactory, actorFactory);
                                    final Object message = messageRecord.getMessage();

                                    try {
                                        actor.process(actorContext, message);
                                    } catch (final Throwable t) {
                                        Logger.getAnonymousLogger().log(
                                                Level.WARNING,
                                                "Actor (" + actorId + ") failed to process message: " + message, t);
                                    }

                                    if (terminate.get()) {
                                        actorsMap.remove(actorId);
                                    }
                                } catch (final Throwable t) {
                                    Logger.getAnonymousLogger().log(
                                            Level.WARNING,
                                            "Error processing actor (" + actorId + ")", t);
                                } finally {
                                    actorsInProcess.remove(actorId);
                                }
                            } else {
                                messageRecords.add(messageRecord);
                            }
                        }
                    }
                });
            }
        }

        currentState = State.Stopped;
    }

    private static class DefaultActorContext implements ActorContext {

        private final ActorId actorId;
        private final ConcurrentMap<ActorId, Actor> actorsMap;
        private final Set<MessageRecord> messageRecords;
        private final AtomicBoolean terminate;
        private final ActorIdFactory actorIdFactory;
        private final ActorFactory actorFactory;

        public DefaultActorContext(ActorId actorId, ConcurrentMap<ActorId, Actor> actorsMap, Set<MessageRecord> messageRecords, AtomicBoolean terminate, ActorIdFactory actorIdFactory, ActorFactory actorFactory) {
            this.actorId = actorId;
            this.actorsMap = actorsMap;
            this.messageRecords = messageRecords;
            this.terminate = terminate;
            this.actorIdFactory = actorIdFactory;
            this.actorFactory = actorFactory;
        }

        public ActorId getMyActorId() {
            return actorId;
        }

        public ActorId createActor(final ActorType actorType) {
            final ActorId newActorId = actorIdFactory.createActorId();
            final Actor newActor = actorFactory.createActor(actorType);

            actorsMap.put(newActorId, newActor);
            return actorId;
        }

        public void sendMessage(final ActorId recipient, final Object message) {
            messageRecords.add(new MessageRecord(recipient, message));
        }

        public void terminate() {
            terminate.set(true);
        }
    }

    private static class MessageRecord {

        private final ActorId actorId;
        private final Object message;

        public MessageRecord(ActorId actorId, Object message) {
            this.actorId = actorId;
            this.message = message;
        }

        public ActorId getActorId() {
            return actorId;
        }

        public Object getMessage() {
            return message;
        }
    }
}
