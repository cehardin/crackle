package org.crackle.net.impl.basic;

import org.crackle.net.du.Bits;
import org.crackle.net.impl.basic.BasicFrame;

/**
 *
 * @author chad
 */
public class BasicBits implements Bits, Cloneable {

    private BasicFrame frame;

    public BasicFrame getFrame() {
        return frame;
    }

    public void setFrame(BasicFrame frame) {
        this.frame = frame;
    }

    @Override
    protected BasicBits clone() {
        BasicBits clone = new BasicBits();
        clone.setFrame(frame.clone());
        return clone;
    }
    
    
    
}
