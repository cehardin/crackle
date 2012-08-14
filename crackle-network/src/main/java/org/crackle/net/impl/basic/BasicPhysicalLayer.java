/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import java.util.HashSet;
import java.util.Set;
import org.crackle.net.layer.DataLinkLayer;
import org.crackle.net.layer.PhysicalLayer;

/**
 *
 * @author chad
 */
public class BasicPhysicalLayer implements PhysicalLayer<BasicBits> {
    private Set<DataLinkLayer<?, BasicBits>> dataLinkLayers = new HashSet<DataLinkLayer<?, BasicBits>>();
    
    public void transmit(BasicBits bits) {
        for(DataLinkLayer<?, BasicBits> dataLinkLayer : dataLinkLayers) {
            dataLinkLayer.receive(bits.clone());
        }
    }

    public Set<DataLinkLayer<?, BasicBits>> getDataLinkLayers() {
        return dataLinkLayers;
    }

    public void setDataLinkLayer(Set<DataLinkLayer<?, BasicBits>> dataLinkLayers) {
        this.dataLinkLayers = dataLinkLayers;
    }
    
}
