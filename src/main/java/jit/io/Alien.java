package jit.io;

import java.io.Serializable;

public class Alien implements Serializable {
    // 627070861952243330
    // 3259740827199711944
    // UID not changed with realization toString(). Only on adding static field. Statics btw don't included to serialized object.
    /**
     * If I uncomment this method and serialize it to file by FreezeAlien. This method will be not touched at all.
     * Serialization saves fields to byte[] probably. And there is Externalizable if I want to Override 
     */
    @Override
    public String toString() {
        String s = "hello";
        int h = 3;
        int k = 4;
        return s+1;
    }
    static int changedQuestionMark = 1;
} ///:~
