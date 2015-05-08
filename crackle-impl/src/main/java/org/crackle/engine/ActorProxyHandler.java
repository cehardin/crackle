/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.engine;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;
import org.crackle.Actor;

/**
 *
 * @author Chad
 */
public class ActorProxyHandler implements InvocationHandler {

    private final ActorContainer actorInstance;

    public ActorProxyHandler(ActorContainer actorInstance) {
        this.actorInstance = actorInstance;
    }
    
    public Object invoke(
            final Object proxy,
            final Method method,
            final Object[] args) throws Throwable {

        if(!actorInstance.tryRunning(() -> method.invoke(actorInstance.getActorImplementation(), args))) {
            actorInstance.addMessage(new ActorInvokeCommand(method, args));
        }
        

        return null;
    }

}
