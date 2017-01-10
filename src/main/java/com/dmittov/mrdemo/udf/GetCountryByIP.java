package com.dmittov.mrdemo.udf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by mittov on 31/12/2016.
 * There is no strong reason to use external csv parser in UDF.
 * Also I use direct constant paths instead of storing configuration in HDFS.
 */

@Description(
        name="GetCountryByIP",
        value="Returns country name by ip address, works with ipv4 only.",
        extended="select GetCountryByIP('8.8.8.8') from foo limit 1;"
)
public class GetCountryByIP extends UDF {

    private Map<Long, String> geobase = null;
    // I'm going to use binary search, so use the specific Set implementation
    // and it's not an abstraction leak
    private TreeSet<Long> geobase_keys = null;
    private String geobase_path = "hdfs:///user/root/input/geobase/geobase.csv";

    protected void setGeobase(final Map<Long, String> geobase) {
        this.geobase = geobase;
        this.geobase_keys = new TreeSet<Long>(this.geobase.keySet());
    }

    protected Map<Long, String> loadGeobase(String geobase_path) throws IOException {
        /*
        Java 8
        try (Stream<String> stream = reader.lines()) {
            stream.forEach(func);
        }
        */
        Map<Long, String> geobase = new HashMap<>();
        Path path = new Path(geobase_path);
        FileSystem fs = FileSystem.get(new Configuration());
        String sep = ",";
        // There is no reason
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(sep);
                Long key = Long.parseLong(record[0].replaceAll("\"", ""));
                String value = record[3].replaceAll("\"", "");
                geobase.put(key, value);
            }
        }
        return geobase;
    }

    public String getCountry(Long ip32) {
        Long key = this.geobase_keys.floor(ip32);
        return this.geobase.get(key);
    }

    public Text evaluate(Text remotehost) throws IOException {
        if(remotehost == null) {
            return null;
        }
        if (this.geobase == null) {
            this.setGeobase(this.loadGeobase(this.geobase_path));
        }
        // Factory method + Strategy inside Ip.parse() in case of ipv4/ipv6,
        // but I have geobase for ipv4 only and ipv4 log records,
        // so there is no reason to handle ipv6 case
        Long ip32 = Ip.parseIpV4(remotehost.toString());
        return new Text(this.getCountry(ip32));
    }
}
