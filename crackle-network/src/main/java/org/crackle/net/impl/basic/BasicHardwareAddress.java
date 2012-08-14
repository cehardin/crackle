/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

/**
 *
 * @author chad
 */
public class BasicHardwareAddress implements Cloneable {
    public static BasicHardwareAddress All = new BasicHardwareAddress();
    @Override
    public BasicHardwareAddress clone() {
        BasicHardwareAddress clone = new BasicHardwareAddress();
        
        return clone;
    }
    
}
