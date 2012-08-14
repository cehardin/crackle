/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Data;
import org.crackle.net.du.Segment;

/**
 *
 * @author chad
 */
public interface PresentationLayer<DT extends Data, D extends Data, DR extends Data> {

    void transmit(DT data);

    void receive(DR data);

    SessionLayer<D, DR, ?> getSessionLayer();

    void setSessionLayer(SessionLayer<D, DR, ?> sessionLayer);
    
    ApplicationLayer<?, DT, D> getApplicationLayer();
    
    void setApplicationLayer(ApplicationLayer<?,DT, D> applicationLayer);
}
