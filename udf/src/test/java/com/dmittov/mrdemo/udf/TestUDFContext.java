package com.dmittov.mrdemo.udf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by mittov on 13/01/2017.
 */
@Configuration
// @Import(UDFContext.class)
public class TestUDFContext {

    @Bean
    public GeobaseLoader geobaseLoaderIPv4() {
        return new PathGeobaseLoader("../docker-hive/provision/IP2LOCATION-LITE-DB1.CSV");
    }

    @Bean
    public NavigableMap<Long, String> geobaseIPv4() throws IOException {
        return new TreeMap<>(geobaseLoaderIPv4().loadGeoBase());
    }

    @Bean
    public CountryDecoder countryDecoderIPv4() {
        return new CountryDecoderIPv4();
    }

    @Bean
    public CountryDecoder countryDecoder() {
        return new CountryDecoderIPv4();
    }


    @Bean
    public IPv4Parser ipv4Parser() {
        return new IPv4Parser();
    }

    @Bean
    public IPParser ipParser() {
        return new IPv4Parser();
    }
}