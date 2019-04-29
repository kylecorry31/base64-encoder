package com.kylecorry.base64;

import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Base64EncoderTest {

    @Test
    void canEncodeString(){
        assertEquals(Base64.getEncoder().encodeToString("Api".getBytes()), Base64Encoder.encode("Api"));
        assertEquals(Base64Encoder.encode("Ap"), Base64.getEncoder().encodeToString("Ap".getBytes()));
        assertEquals(Base64Encoder.encode("A"), Base64.getEncoder().encodeToString("A".getBytes()));

        for (int i = 0; i < 100; i++) {
            String s = UUID.randomUUID().toString();
            assertEquals(Base64Encoder.encode(s), Base64.getEncoder().encodeToString(s.getBytes()));
        }
    }

    @Test
    void canRead6Bits(){
        assertEquals(0b000000, Base64Encoder.read6Bits(0, new byte[]{0}));
        assertEquals(0b000000, Base64Encoder.read6Bits(1, new byte[]{0}));
        assertEquals(0b000000, Base64Encoder.read6Bits(0, new byte[]{1}));
        assertEquals(0b010000, Base64Encoder.read6Bits(1, new byte[]{1}));
        assertEquals(0b010101, Base64Encoder.read6Bits(1, new byte[]{1, 0b01010000}));
    }

    @Test
    void throwsIfReadingOutOfRange(){
        assertThrows(IndexOutOfBoundsException.class, () -> Base64Encoder.read6Bits(-1, new byte[]{0}));
        assertThrows(IndexOutOfBoundsException.class, () -> Base64Encoder.read6Bits(4, new byte[]{0, 1, 2}));
    }


}