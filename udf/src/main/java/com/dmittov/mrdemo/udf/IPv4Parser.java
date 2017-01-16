package com.dmittov.mrdemo.udf;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by mittov on 13/01/2017.
 */
@Component
public class IPv4Parser implements IPParser {

    @Override
    public Long parse(String remoteHost) {
        int offset = 24;
        long ip32 = 0;
        String[] octets = remoteHost.split(Pattern.quote("."));
        for (String octet : octets) {
            ip32 += ((long) Integer.parseInt(octet)) << offset;
            offset -= 8;
        }
        return ip32;
    }
}
