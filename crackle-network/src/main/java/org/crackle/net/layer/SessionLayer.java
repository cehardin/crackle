/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Data;
import org.crackle.net.du.Datagram;
import org.crackle.net.du.Segment;

/**
 *
 * @author chad
 */
public interface SessionLayer<D extends Data, S extends Segment> {

    void transmit(D data);

    void receive(S data);

    TransportLayer<S, ?> getTransportLayer();

    void setTransportLayer(TransportLayer<S, ?> transportLayer);

    PresentationLayer<?, D> getPresentationLayer();

    void setPresentationLayer(PresentationLayer<?, D> presentationLayer);
}
