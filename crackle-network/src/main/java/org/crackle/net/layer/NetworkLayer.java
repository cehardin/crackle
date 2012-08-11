/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Datagram;
import org.crackle.net.du.Frame;

/**
 *
 * @author chad
 */
public interface NetworkLayer<D extends Datagram, F extends Frame> {
    void transmit(D datagram);
    void receive(F frame);
}
