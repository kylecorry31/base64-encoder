package com.kylecorry.base64;

/**
 * A base 64 encoder
 */
public class Base64Encoder {

    /**
     * Encode a string into base64 format.
     * @param s The string to encode.
     * @return The input string, encoded into a base64 string.
     */
    public static String encode(String s){
        return encode(s.getBytes());
    }

    /**
     * Encode a byte array into base64 format.
     * @param bytes The byte array to encode.
     * @return The input byte array, encoded into a base64 string.
     */
    public static String encode(byte[] bytes){

        StringBuilder s = new StringBuilder();

        int num6Bits = (int) Math.ceil(bytes.length * Byte.SIZE / 6.0);

        for (int i = 0; i < num6Bits; i++) {
            s.append(lookup(read6Bits(i, bytes)));
        }

        int eqsNeeded = (bytes.length % 3);
        if (eqsNeeded == 2){
            s.append('=');
        } else if (eqsNeeded == 1){
            s.append("==");
        }

        return s.toString();
    }

    /**
     * Read 6 bits from a byte array
     * @param index The 6-bit index to read bits from
     * @param bytes The bytes to get the 6 bits from
     * @return The 6 bits from the values int, left padded with zeros.
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    static byte read6Bits(int index, byte[] bytes){
        int numBits = bytes.length * Byte.SIZE;
        int maxIndex = (int) Math.ceil(numBits / 6.0);
        if (index < 0 || index >= maxIndex){
            throw new IndexOutOfBoundsException();
        }

        final int bit6mask = 0b00111111;

        int startingBit = index * 6;
        int startingByteIndex = startingBit / Byte.SIZE;
        int startingOffset = startingBit % Byte.SIZE;

        int startingMask = 0b11111100 >> startingOffset;

        int bits = (bytes[startingByteIndex] & startingMask) << startingOffset >> (Byte.SIZE - 6);
        int endingBit = (index + 1) * 6;
        int endingByteIndex = endingBit / Byte.SIZE;
        if (endingByteIndex != startingByteIndex && endingByteIndex < bytes.length) {
            int endingOffset = Byte.SIZE - endingBit % Byte.SIZE;
            int endingMask = bit6mask << endingOffset;
            int endingBits = (bytes[endingByteIndex] & endingMask);
            bits |= endingBits >> endingOffset;
        }

        return (byte) (bits & bit6mask);
    }

    /**
     * Lookup a value in a base64 table.
     * @param val The value to lookup [0, 63]
     * @return The character associated with the value in base64.
     */
    private static char lookup(int val){
        if (val <= 25){
            return (char) ('A' + val);
        } else if (val <= 51){
            return (char) ('a' + val - 26);
        } else if (val <= 61){
            return (char) ('0' + val - 52);
        } else if (val == 62){
            return '+';
        } else {
            return '/';
        }
    }

}
