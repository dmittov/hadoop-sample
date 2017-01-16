package com.dmittov.mrdemo.udf;

import org.apache.hadoop.io.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created by mittov on 13/01/2017.
 */
@Component
public class CountryDecoderImpl implements CountryDecoder {

    @Autowired
    private IPParser ipParser;
    @Autowired
    Map<Class, NavigableMap<BigInteger, String>> geobase;

    @Override
    public String getCountry(String remoteHost) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName(remoteHost);
        NavigableMap<BigInteger, String> geobase = this.geobase.get(addr.getClass());
        BigInteger ip128 = ipParser.parse(remoteHost);
        return geobase.floorEntry(ip128).getValue();
    }
}
