/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Datagram;
import org.crackle.net.du.Segment;

/**
 *
 * @author chad
 */
public interface TransportLayer<S extends Segment, D extends Datagram> {
    void transmit(S segment);
    void receive(D datagram);
}
