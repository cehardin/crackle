/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Bits;
import org.crackle.net.du.Datagram;
import org.crackle.net.du.Frame;
import org.crackle.net.du.Segment;

/**
 *
 * @author chad
 */
public interface NetworkLayer<S extends Segment, D extends Datagram, F extends Frame> {

    void transmit(S segment);

    void receive(F frame);

    DataLinkLayer<D, F, ?> getDataLinkLayer();

    void setDataLinkLayer(DataLinkLayer<D, F, ?> dataLinkLayer);

    TransportLayer<?, S, D> getTransportLayer();

    void setTransportLayer(TransportLayer<?, S, D> transportLayer);
}
