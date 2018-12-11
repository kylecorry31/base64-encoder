package com.kylecorry.base64;

public class Base64Encoder {

    public static String encode(String s){
        return encode(s.getBytes());
    }

    public static String encode(byte[] bytes){

        StringBuilder bitBuilder = new StringBuilder();
        for (byte b: bytes) {
            bitBuilder.append(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));
        }

        int eqsNeeded = (bytes.length % 3);
        if (eqsNeeded == 2){
            eqsNeeded = 1;
        } else if (eqsNeeded == 1){
            eqsNeeded = 2;
        }

        while (bitBuilder.length() % 6 != 0){
            bitBuilder.append("0");
        }

        String bitString = bitBuilder.toString();
        StringBuilder base64sb = new StringBuilder();

        for (int i = 0; i < bitString.length(); i += 6){
            String bits = bitString.substring(i, Math.min(i + 6, bitString.length()));

            int val  = Integer.parseInt(bits, 2);
            base64sb.append(lookup(val));
        }

        for (int i = 0; i < eqsNeeded; i++) {
            base64sb.append('=');
        }



        return base64sb.toString();
    }

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
