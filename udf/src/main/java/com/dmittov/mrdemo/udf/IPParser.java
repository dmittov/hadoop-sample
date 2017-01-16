package com.dmittov.mrdemo.udf;

/**
 * Created by mittov on 13/01/2017.
 */
// Now I can replace custom class transparently
public interface IPParser {
    Long parse(String remoteHost);
}
