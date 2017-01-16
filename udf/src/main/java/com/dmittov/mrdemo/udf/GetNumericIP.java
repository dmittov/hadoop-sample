package com.dmittov.mrdemo.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.UnknownHostException;

/**
 * Created by mittov on 10/01/2017.
 */
@Description(
        name="GetNumericIP",
        value="Returns decoded bigint ip, works with ipv4 only",
        extended="select GetNumericIP('8.8.8.8') from foo limit 1;"
)
public class GetNumericIP extends UDF {

    private IPParser ipParser = null;

    public BytesWritable evaluate(Text remoteHost) throws UnknownHostException {
        if(remoteHost == null) {
            return null;
        }
        if (ipParser == null) {
            ApplicationContext context = new AnnotationConfigApplicationContext(UDFContext.class);
            ipParser = context.getBean(IPParser.class);
        }
        return new BytesWritable(ipParser.parse(remoteHost.toString()).toByteArray());
    }

}
