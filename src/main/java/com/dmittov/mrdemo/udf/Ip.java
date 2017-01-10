package com.dmittov.mrdemo.udf;

import java.util.regex.Pattern;

/**
 * Created by mittov on 31/12/2016.
 */
public class Ip {

    public static long parseIpV4(String remotehost) {
        int offset = 24;
        long ip32 = 0;
        String[] octets = remotehost.split(Pattern.quote("."));
        for (String octet : octets) {
            ip32 += ((long) Integer.parseInt(octet)) << offset;
            offset -= 8;
        }
        return ip32;
    }

}
