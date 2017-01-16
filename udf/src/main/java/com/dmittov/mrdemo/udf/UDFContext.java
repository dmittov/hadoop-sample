package com.dmittov.mrdemo.udf;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by mittov on 13/01/2017.
 */
@Configuration
public class UDFContext {

    @Bean
    public GeobaseLoader geobaseLoaderIPv4() {
        return new PathGeobaseLoader("hdfs:///user/root/input/geobase/geobase.csv");
    }

    @Bean
    public NavigableMap<Long, String> geobaseIPv4() throws HiveException {
        try {
            return new TreeMap<>(geobaseLoaderIPv4().loadGeoBase());
        } catch (IOException err) {
            throw new HiveException("Loading geobase exception");
        }
    }

    @Bean
    public IPv4Parser ipv4Parser() {
        return new IPv4Parser();
    }

    // Factory method + Strategy inside in case of ipv4/ipv6,
    // but I have geobase for ipv4 only and ipv4 log records,
    // so there is no reason to handle ipv6 case.

    @Bean
    public CountryDecoder countryDecoder() {
        return new CountryDecoderIPv4();
    }

    @Bean
    public IPParser ipParser() {
        return new IPv4Parser();
    }
}
