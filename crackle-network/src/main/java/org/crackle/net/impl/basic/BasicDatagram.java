/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import org.crackle.net.du.Datagram;

/**
 *
 * @author chad
 */
public class BasicDatagram implements Datagram, Cloneable {
    private BasicProtocolAddress senderProtocolAddress;
    private BasicProtocolAddress targetProtocolAddress;

    public BasicProtocolAddress getSenderProtocolAddress() {
        return senderProtocolAddress;
    }

    public void setSenderProtocolAddress(BasicProtocolAddress senderProtocolAddress) {
        this.senderProtocolAddress = senderProtocolAddress;
    }

    public BasicProtocolAddress getTargetProtocolAddress() {
        return targetProtocolAddress;
    }

    public void setTargetProtocolAddress(BasicProtocolAddress targetProtocolAddress) {
        this.targetProtocolAddress = targetProtocolAddress;
    }
    
    
    
}
