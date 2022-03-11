package ch.springboot.receipe.utils;

public class Utils {

    public static Byte[] autoUnboxingByte(byte[] bytes) {
        Byte[] bytesBoxed = new Byte[bytes.length];
        int i = 0;

        for(byte by : bytes) {
            bytesBoxed[i++] = by;
        }

        return bytesBoxed;
    }

    public static byte[] autoBoxingByte(Byte[] bytes) {
        byte[] bytesBoxed = new byte[bytes.length];
        int i = 0;

        for(byte by : bytes) {
            bytesBoxed[i++] = by;
        }

        return bytesBoxed;
    }

}
