/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl;

import org.crackle.net.du.Datagram;
import org.crackle.net.du.Frame;
import org.crackle.net.layer.DataLinkLayer;
import org.crackle.net.layer.NetworkLayer;
import org.crackle.net.layer.TransportLayer;

/**
 *
 * @author chad
 */
public abstract class AbstractNetworkLayer<D extends Datagram, F extends Frame> implements NetworkLayer<D, F> {
    private DataLinkLayer<F, ?> dataLinkLayer;
    private TransportLayer<?,D> transportLayer;
    
    public DataLinkLayer<F, ?> getDataLinkLayer() {
        return dataLinkLayer;
    }

    public void setDataLinkLayer(DataLinkLayer<F, ?> dataLinkLayer) {
        this.dataLinkLayer = dataLinkLayer;
    }

    public TransportLayer<?, D> getTransportLayer() {
        return transportLayer;
    }

    public void setTransportLayer(TransportLayer<?, D> transportLayer) {
        this.transportLayer = transportLayer;
    }
    
}
