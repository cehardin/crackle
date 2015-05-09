package org.crackle;

import java.io.Serializable;

/**
 *
 * @author Chad
 */
public interface Actor extends Serializable {
    
    void initialize(Context context) throws Exception;
    
    void message(Context context, Message message) throws Exception;
    
    void suspend(Context context) throws Exception;
    
    void resume(Context context) throws Exception;
    
    void terminate(Context context) throws Exception;
}
