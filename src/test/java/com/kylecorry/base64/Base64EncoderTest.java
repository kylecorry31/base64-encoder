package com.kylecorry.base64;

import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

public class Base64EncoderTest {

    @Test
    public void testEncodingString(){
        assertEquals(Base64Encoder.encode("Man"), Base64.getEncoder().encodeToString("Man".getBytes()));
        assertEquals(Base64Encoder.encode("Ma"), Base64.getEncoder().encodeToString("Ma".getBytes()));
        assertEquals(Base64Encoder.encode("M"), Base64.getEncoder().encodeToString("M".getBytes()));
    }


}