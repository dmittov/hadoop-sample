package com.dmittov.mrdemo.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;

/**
 * Created by mittov on 10/01/2017.
 */
@Description(
        name="GetNumericIP",
        value="Returns decoded bigint ip, works with ipv4 only",
        extended="select GetNumericIP('8.8.8.8') from foo limit 1;"
)
public class GetNumericIP extends UDF {

    public LongWritable evaluate(Text remotehost) {
        if(remotehost == null) {
            return null;
        }
        Long ip32 = Ip.parseIpV4(remotehost.toString());
        return new LongWritable(ip32);
    }

}
