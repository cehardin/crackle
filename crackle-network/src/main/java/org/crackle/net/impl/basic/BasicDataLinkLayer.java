/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import org.crackle.net.du.Bits;
import org.crackle.net.du.Frame;
import org.crackle.net.impl.basic.BasicUtils;
import org.crackle.net.layer.DataLinkLayer;
import org.crackle.net.layer.NetworkLayer;
import org.crackle.net.layer.PhysicalLayer;

/**
 *
 * @author chad
 */
public class BasicDataLinkLayer implements DataLinkLayer<BasicFrame, BasicBits> {
    
    private BasicHardwareAddress hardwareAddress;

    public void transmit(BasicFrame frame) {
        BasicBits bits = new BasicBits();
        frame.setSenderAddress(hardwareAddress);
        bits.setFrame(frame);
        getPhysicalLayer().transmit(bits);
    }

    public void receive(BasicBits bits) {
        BasicFrame frame = bits.getFrame();
        BasicHardwareAddress targetAddress = frame.getTargetAddress();
        if(targetAddress.equals(hardwareAddress) || targetAddress.equals(BasicHardwareAddress.All)) {
            getNetworkLayer().receive(frame);
        }
    }

    public PhysicalLayer<BasicBits> getPhysicalLayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPhysicalLayer(PhysicalLayer<BasicBits> physicalLayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NetworkLayer<?, BasicFrame> getNetworkLayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNetworkLayer(NetworkLayer<?, BasicFrame> networkLayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
}
