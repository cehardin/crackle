/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import java.util.Set;
import org.crackle.net.du.Bits;

/**
 *
 * @author chad
 */
public interface PhysicalLayer<B extends Bits> {

    void transmit(B bits);

    Set<DataLinkLayer<?, B>> getDataLinkLayers();

    void setDataLinkLayer(Set<DataLinkLayer<?, B>> dataLinkLayers);
}
