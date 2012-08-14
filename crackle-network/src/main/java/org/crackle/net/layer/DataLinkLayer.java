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
public interface DataLinkLayer<D extends Datagram, F extends Frame, B extends Bits> {

    void transmit(D datagram);

    void receive(B bits);

    PhysicalLayer<B> getPhysicalLayer();

    void setPhysicalLayer(PhysicalLayer<B> physicalLayer);

    NetworkLayer<?, D, F> getNetworkLayer();

    void setNetworkLayer(NetworkLayer<?, D, F> networkLayer);
}
