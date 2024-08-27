package com.globant.domain.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author erillope
 */
public class Serializer {
    public static void serialize(Serializable serializable, String path) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(serializable);
    }
    
    public static Object desSerialize(String source)throws Exception{
        FileInputStream fileIn = new FileInputStream(source);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        return in.readObject();
    }
}
