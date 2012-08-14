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
public interface SessionLayer<DT extends Data, DR extends Data, S extends Segment> {

    void transmit(DT data);

    void receive(S segment);

    TransportLayer<DR, S, ?> getTransportLayer();

    void setTransportLayer(TransportLayer<DR, S, ?> transportLayer);

    PresentationLayer<?, DT, DR> getPresentationLayer();

    void setPresentationLayer(PresentationLayer<?, DT, DR> presentationLayer);
}
