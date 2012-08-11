
package org.crackle;

/**
 *
 * @author chad
 */
public interface ActorContext {
    ActorId getMyActorId();
    void terminate();
    ActorId createActor(ActorType actorType);
    void sendMessage(ActorId recipient, Object message);
}
