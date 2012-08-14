/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import org.crackle.net.du.Frame;

/**
 *
 * @author chad
 */
public class BasicFrame implements Frame, Cloneable {
    private BasicHardwareAddress senderAddress;
    private BasicHardwareAddress targetAddress;
    private BasicDatagram datagram;
    private BasicAddressResolutionRequest addressResolutionRequest;
    private BasicAddressResolutionReply addressResolutionReply;

    public BasicHardwareAddress getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(BasicHardwareAddress senderAddress) {
        this.senderAddress = senderAddress;
    }

    public BasicHardwareAddress getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(BasicHardwareAddress targetAddress) {
        this.targetAddress = targetAddress;
    }

    public BasicDatagram getDatagram() {
        return datagram;
    }

    public void setDatagram(BasicDatagram datagram) {
        this.datagram = datagram;
    }

    public BasicAddressResolutionRequest getAddressResolutionRequest() {
        return addressResolutionRequest;
    }

    public void setAddressResolutionRequest(BasicAddressResolutionRequest addressResolutionRequest) {
        this.addressResolutionRequest = addressResolutionRequest;
    }

    public BasicAddressResolutionReply getAddressResolutionReply() {
        return addressResolutionReply;
    }

    public void setAddressResolutionReply(BasicAddressResolutionReply addressResolutionReply) {
        this.addressResolutionReply = addressResolutionReply;
    }
    
    
    @Override
    protected BasicFrame clone() {
        BasicFrame clone = new BasicFrame();
        clone.setSenderAddress(senderAddress.clone());
        clone.setTargetAddress(targetAddress.clone());
        clone.setDatagram(datagram);
        clone.setAddressResolutionRequest(addressResolutionRequest);
        clone.setAddressResolutionReply(addressResolutionReply);
        return clone;
    }
    
    
}
