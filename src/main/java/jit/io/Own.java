package jit.io;

import java.io.UnsupportedEncodingException;

public class Own {
    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException {
        byte[] aaa = "helle".getBytes("UTF-8");
        byte[] arr = new byte[]{0,'a'}; // char is int, int could easily casted to byte
    }
}
