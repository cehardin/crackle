/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Data;
import org.crackle.net.du.Datagram;
import org.crackle.net.du.Frame;
import org.crackle.net.du.Segment;

/**
 *
 * @author chad
 */
public interface TransportLayer<D extends Data, S extends Segment, DG extends Datagram> {

    void transmit(D data);

    void receive(DG datagram);

    NetworkLayer<S, DG, ?> getNetworkLayer();

    void setNetworkLayer(NetworkLayer<S, DG, ?> networkLayer);

    SessionLayer<?, D, S> getSessionLayer();

    void setSessionLayer(SessionLayer<?, D, S> sessionLayer);
}
