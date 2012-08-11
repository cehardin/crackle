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
    private State currentState = State.Stopped;
    private State desiredState = State.Stopped;
    private Thread processThread;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
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
                desiredState = State.Running;
                currentState = State.Starting;
                break;
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
                final ActorId actorId = messageRecord.getActorId();
                messageRecords.remove(messageRecord);
                if (actorsInProcess.add(actorId)) {
                    final Actor actor = actorsMap.get(actorId);

                    if (actor != null) {
                        final AtomicBoolean terminate = new AtomicBoolean(false);
                        final ActorContext actorContext = new ActorContext() {
                            public ActorId getMyActorId() {
                                return actorId;
                            }

                            public ActorId createActor(ActorType actorType) {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }

                            public void sendMessage(ActorId recipient, Object message) {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }

                            public void terminate() {
                                terminate.set(true);
                            }
                        };
                        executorService.submit(new Runnable() {
                            public void run() {
                                actor.process(actorContext, messageRecord.getMessage());

                                if (terminate.get()) {
                                    actorsMap.remove(actorId);
                                }
                            }
                        });
                    }
                }
            }
        }

        currentState = State.Stopped;
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
