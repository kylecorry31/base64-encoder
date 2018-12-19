package com.kylecorry.base64;

import org.junit.Test;

import java.util.Base64;
import java.util.UUID;

import static org.junit.Assert.*;

public class Base64EncoderTest {

    @Test
    public void testEncodingString(){
        assertEquals(Base64Encoder.encode("Man"), Base64.getEncoder().encodeToString("Man".getBytes()));
        assertEquals(Base64Encoder.encode("Ma"), Base64.getEncoder().encodeToString("Ma".getBytes()));
        assertEquals(Base64Encoder.encode("M"), Base64.getEncoder().encodeToString("M".getBytes()));

        String s = UUID.randomUUID().toString();
        assertEquals(Base64Encoder.encode(s), Base64.getEncoder().encodeToString(s.getBytes()));
    }

    @Test
    public void testReading6Bits(){
        assertEquals(0b111111, Base64Encoder.read6Bits(0, 0b111111 << 26));
        assertEquals(0b110111, Base64Encoder.read6Bits(1, 0b110111 << 20));
        assertEquals(0, Base64Encoder.read6Bits(0, 0b110111 << 20));
        assertEquals(0b111011, Base64Encoder.read6Bits(2, 0b111011 << 14));
        assertEquals(0b111101, Base64Encoder.read6Bits(3, 0b111101 << 8));
    }

    @Test
    public void testPackBytesIntoInt(){
        assertEquals(0xffeedd00, Base64Encoder.packBytesIntoInt(0xff, 0xee, 0xdd));
        assertEquals(0x00876500, Base64Encoder.packBytesIntoInt(0x00, 0x87, 0x65));
    }


}