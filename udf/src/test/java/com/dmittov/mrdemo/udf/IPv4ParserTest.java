package com.dmittov.mrdemo.udf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by mittov on 13/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUDFContext.class)
public class IPv4ParserTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private
    @Value("#{ipv4Parser}")
    IPParser ipv4Parser;

    @Test
    public void parse() throws Exception {
        assertEquals(ipv4Parser.parse("0.0.0.32").longValue(), 32l);
        assertEquals(ipv4Parser.parse("127.0.0.1").longValue(), 2130706433l);
        assertEquals(ipv4Parser.parse("255.255.255.0").longValue(), 4294967040l);
        exception.expect(NumberFormatException.class);
        ipv4Parser.parse("2a02:6b8::40c:18c0:f311:d05f:b3d");
    }
}
