package com.dmittov.mrdemo.udf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by mittov on 13/01/2017.
 */
@Configuration
public class TestUDFContext {

    @Bean
    public GeobaseLoader geobaseLoaderIPv4() {
        return new PathGeobaseLoader("../docker-hive/provision/IP2LOCATION-LITE-DB1.CSV");
    }

    @Bean
    public GeobaseLoader geobaseLoaderIPv6() {
        return new PathGeobaseLoader("../docker-hive/provision/IP2LOCATION-LITE-DB1.IPV6.CSV");
    }

    @Bean
    public Map<Class, NavigableMap<BigInteger, String>> geobase() throws IOException {
        Map<Class, NavigableMap<BigInteger, String>> geobase = new HashMap<>();
        geobase.put(Inet4Address.class, new TreeMap<>(geobaseLoaderIPv4().loadGeoBase()));
        geobase.put(Inet6Address.class, new TreeMap<>(geobaseLoaderIPv6().loadGeoBase()));
        return geobase;
    }

    @Bean
    public CountryDecoder countryDecoder() {
        return new CountryDecoderImpl();
    }

    @Bean
    public IPParser ipParser() {
        return new IPParserImpl();
    }
}