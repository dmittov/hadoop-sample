package com.dmittov.mrdemo.udf;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by mittov on 13/01/2017.
 */
// Now I can replace custom class transparently
public interface IPParser {
    BigInteger parse(String remoteHost) throws UnknownHostException;
}
