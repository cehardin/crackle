/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Bits;
import org.crackle.net.du.Frame;

/**
 *
 * @author chad
 */
public interface DataLinkLayer<F extends Frame, B extends Bits> {

    void transmit(F frame);

    void receive(B bits);

    PhysicalLayer<B> getPhysicalLayer();

    void setPhysicalLayer(PhysicalLayer<B> physicalLayer);

    NetworkLayer<?, F> getNetworkLayer();

    void setNetworkLayer(NetworkLayer<?, F> networkLayer);
}
