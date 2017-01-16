package com.dmittov.mrdemo.udf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by mittov on 13/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUDFContext.class)
public class IPParserTest {

    @Autowired
    private IPParser ipParser;

    @Test
    public void parse() throws Exception {
        assertArrayEquals(new byte[] {32}, ipParser.parse("0.0.0.32").toByteArray());
        assertArrayEquals(new byte[] {127, 0, 0, 1}, ipParser.parse("127.0.0.1").toByteArray());
        assertArrayEquals(new byte[] {0, -1, -1, -1, 0}, ipParser.parse("255.255.255.0").toByteArray());
        assertArrayEquals(new byte[] {42, 0, 20, 80, 64, 15, 8, 2, 0, 0, 0, 0, 0, 0, 32, 14},
                ipParser.parse("2a00:1450:400f:802::200e").toByteArray());
    }
}
