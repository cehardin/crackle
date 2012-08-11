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
public interface PresentationLayer<DT extends Data, DR extends Data> {
    void transmit(DT data);
    void receive(DR data);
}
