/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import java.util.Map;
import org.crackle.net.du.Frame;
import org.crackle.net.impl.AbstractNetworkLayer;
import org.crackle.net.layer.DataLinkLayer;
import org.crackle.net.layer.NetworkLayer;
import org.crackle.net.layer.TransportLayer;

/**
 *
 * @author chad
 */
public class BasicNetworkLayer extends AbstractNetworkLayer<BasicDatagram, BasicFrame> {
    private BasicProtocolAddress protocolAddress;
    private Map<BasicProtocolAddress, BasicHardwareAddress> addressMap;
    
    public void transmit(BasicDatagram datagram) {
        BasicProtocolAddress targetProtocolAddress = datagram.getTargetProtocolAddress();
        BasicHardwareAddress targetHardwareAddress = addressMap.get(targetProtocolAddress);
        BasicFrame frame = new BasicFrame();
        
        if(targetHardwareAddress == null) {
            BasicAddressResolutionRequest arpRequest = new BasicAddressResolutionRequest();
            arpRequest.setTargetProtocolAddress(targetProtocolAddress);
            frame.setTargetAddress(BasicHardwareAddress.All);
            frame.setAddressResolutionRequest(arpRequest);
        }
        else {
            frame.setTargetAddress(targetHardwareAddress);
            frame.setDatagram(datagram);
        }
        
        getDataLinkLayer().transmit(frame);
    }

    public void receive(BasicFrame frame) {
        BasicAddressResolutionRequest addressResolutionRequest = frame.getAddressResolutionRequest();
        BasicAddressResolutionReply addressResolutionReply = frame.getAddressResolutionReply();

        if(addressResolutionRequest != null) {
            BasicFrame arpReplyFrame = new BasicFrame();
            BasicAddressResolutionReply arpReply = new BasicAddressResolutionReply();
            arpReplyFrame.setSenderAddress(null);
            arpReplyFrame.setTargetAddress(frame.getSenderAddress());
            arpReply.setTargetHardwareAddress(null);
            arpReply.setTargetProtocolAddress(protocolAddress);
            arpReplyFrame.setAddressResolutionReply(addressResolutionReply);
            getDataLinkLayer().transmit(arpReplyFrame);
        }
        
        if(addressResolutionReply != null) {
            addressMap.put(addressResolutionReply.getTargetProtocolAddress(), 
                    addressResolutionReply.getTargetHardwareAddress());
        }
    }
}
