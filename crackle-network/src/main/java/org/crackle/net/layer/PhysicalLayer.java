/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.layer;

import java.util.Set;
import org.crackle.net.du.Bits;
import org.crackle.net.du.Frame;

/**
 *
 * @author chad
 */
public interface PhysicalLayer<B extends Bits> {

    void transmit(B bits);

    DataLinkLayer<?, ?, B> getDataLinkLayer();

    void setDataLinkLayer(DataLinkLayer<?, ?, B> dataLinkLayer);
}
