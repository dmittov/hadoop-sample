package com.dmittov.mrdemo.udf;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by mittov on 13/01/2017.
 */
public interface GeobaseLoader {
    Map<BigInteger, String> loadGeoBase() throws IOException;
}
