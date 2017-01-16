package com.dmittov.mrdemo.udf;

import org.apache.hadoop.io.Text;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by mittov on 13/01/2017.
 */
@Component
public class CountryDecoderIPv4 implements CountryDecoder {

    private @Value("#{ipParser}") IPParser ipParser;
    // I'm going to use binary search, so use the specific Set implementation
    // and it's not an abstraction leak
    private @Value("#{geobaseIPv4}") TreeMap<Long, String> geobase;

    @Override
    public String getCountry(String remoteHost) {
        Long ip32 = ipParser.parse(remoteHost);
        return this.geobase.floorEntry(ip32).getValue();
    }
}
