package com.dmittov.mrdemo.udf;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

/**
 * Created by mittov on 10/01/2017.
 */
public class IpTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void parseIpV4() throws Exception {
        assertEquals(Ip.parseIpV4("0.0.0.32"), 32);
        assertEquals(Ip.parseIpV4("127.0.0.1"), 2130706433);
        assertEquals(Ip.parseIpV4("255.255.255.0"), 4294967040l);
        exception.expect(NumberFormatException.class);
        Ip.parseIpV4("2a02:6b8::40c:18c0:f311:d05f:b3d");
    }
}