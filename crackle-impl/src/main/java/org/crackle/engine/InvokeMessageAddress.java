package org.crackle.engine;

import org.crackle.Actor;
import org.crackle.Address;

/**
 *
 * @author Chad
 */
public class InvokeMessageAddress<T extends Actor> implements Address<T> {

    private final Actor actorProxy;
    private final InvokeMessageActorContext actorContext;

    public InvokeMessageAddress(Actor actor, InvokeMessageActorContext actorContext) {
        this.actor = actor;
        this.actorContext = actorContext;
    }

    public T lookup() {
       
    }
}
