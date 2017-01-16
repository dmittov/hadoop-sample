package com.dmittov.mrdemo.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by mittov on 31/12/2016.
 * There is no strong reason to use external csv parser in UDF.
 * Also I use direct constant paths instead of storing configuration in HDFS.
 */

@Description(
        name = "GetCountryByIP",
        value = "Returns country name by ip address, works with ipv4 only.",
        extended = "select GetCountryByIP('8.8.8.8') from foo limit 1;"
)
public class GetCountryByIP extends UDF {

    private CountryDecoder countryDecoder = null;

    public Text evaluate(Text remotehost) {
        if (remotehost == null) {
            return null;
        }
        if (countryDecoder == null) {
            ApplicationContext context = new AnnotationConfigApplicationContext(UDFContext.class);
            countryDecoder = context.getBean(CountryDecoder.class);
        }
        return new Text(countryDecoder.getCountry(remotehost.toString()));
    }
}
