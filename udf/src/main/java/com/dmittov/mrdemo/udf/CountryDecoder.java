package com.dmittov.mrdemo.udf;

import java.net.UnknownHostException;

/**
 * Created by mittov on 13/01/2017.
 */
public interface CountryDecoder {
    String getCountry(String remotehost) throws UnknownHostException;
}
