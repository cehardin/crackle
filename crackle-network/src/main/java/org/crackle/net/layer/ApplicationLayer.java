/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import org.crackle.net.du.Data;

/**
 *
 * @author chad
 */
public interface ApplicationLayer<DT extends Data, DR extends Data> {

    void transmit(DT data);

    void receive(DR data);

    PresentationLayer<DR, ?> getPresentationLayer();

    void setPresentationLayer(PresentationLayer<DR, ?> presentationLayer);
}
