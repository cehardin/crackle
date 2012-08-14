/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

/**
 *
 * @author chad
 */
public class BasicAddressResolutionReply {
    private BasicProtocolAddress targetProtocolAddress;
    private BasicHardwareAddress targetHardwareAddress;

    public BasicProtocolAddress getTargetProtocolAddress() {
        return targetProtocolAddress;
    }

    public void setTargetProtocolAddress(BasicProtocolAddress targetProtocolAddress) {
        this.targetProtocolAddress = targetProtocolAddress;
    }

    public BasicHardwareAddress getTargetHardwareAddress() {
        return targetHardwareAddress;
    }

    public void setTargetHardwareAddress(BasicHardwareAddress targetHardwareAddress) {
        this.targetHardwareAddress = targetHardwareAddress;
    }
    
    
}
