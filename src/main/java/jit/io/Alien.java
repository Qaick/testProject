package jit.io;//: io/Alien.java
// A serializable class.

import java.io.Serializable;

public class Alien implements Serializable {
    private static final long serialVersionUID = 627070861952243330L;
    /**
     * If I uncomment this method and serialize it to file by FreezeAlien. This method will be not touched at all.
     * Serialization saves fields to byte[] probably. And there is Externalizable if I want to Override 
     */
    @Override
    public String toString() {
        return "very cool";
    }
} ///:~
