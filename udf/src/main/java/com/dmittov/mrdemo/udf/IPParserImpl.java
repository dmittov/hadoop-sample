package com.dmittov.mrdemo.udf;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by mittov on 16/01/2017.
 */
public class IPParserImpl implements IPParser {

    @Override
    public BigInteger parse(String remoteHost) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(remoteHost);
        return new BigInteger(1, address.getAddress());
    }
}
