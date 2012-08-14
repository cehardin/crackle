/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crackle.net.impl.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author chad
 */
public class BasicUtils {
    public static byte[] serialize(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(object);
            oos.close();
            
            return baos.toByteArray();
        } catch (IOException iOException) {
            throw new RuntimeException("Unable to serialize: "+object, iOException);
        }
    }
    
    public static Object deserialize(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            return ois.readObject();
        } catch (IOException iOException) {
            throw new RuntimeException("Unable to serialize: "+data, iOException);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException("Unable to serialize: "+data, classNotFoundException);
        }
    }
}
