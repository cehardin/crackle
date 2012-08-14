/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Bits;
import org.crackle.net.du.Datagram;
import org.crackle.net.du.Frame;

/**
 *
 * @author chad
 */
public interface NetworkLayer<D extends Datagram, F extends Frame> {

    void transmit(D datagram);

    void receive(F frame);

    DataLinkLayer<F, ?> getDataLinkLayer();

    void setDataLinkLayer(DataLinkLayer<F, ?> dataLinkLayer);

    TransportLayer<?, D> getTransportLayer();

    void setTransportLayer(TransportLayer<?, D> transportLayer);
}
