package org.crackle;

/**
 *
 * @author Chad
 */
public interface Context {
    
    /**
     * 
     * @throws IllegalStateException 
     */
    void terminate() throws IllegalStateException;
    
    /**
     *
     * @param <T>
     * @param type
     * @return
     */
    <T extends Actor> Address<T> create(Class<T> type);
}
