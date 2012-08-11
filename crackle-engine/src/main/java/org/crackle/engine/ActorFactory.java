/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.engine;

import org.crackle.Actor;
import org.crackle.ActorType;

/**
 *
 * @author chad
 */
public interface ActorFactory {
    Actor createActor(ActorType actorType);
}
