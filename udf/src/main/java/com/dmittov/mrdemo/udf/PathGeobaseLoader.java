package com.dmittov.mrdemo.udf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mittov on 13/01/2017.
 */
@Component
public class PathGeobaseLoader implements GeobaseLoader {

    protected String geobasePath;

    public PathGeobaseLoader(String geobasePath) {
        this.geobasePath = geobasePath;
    }

    @Override
    public Map<BigInteger, String> loadGeoBase() throws IOException {
        Map<BigInteger, String> geobase = new HashMap<>();
        Path path = new Path(geobasePath);
        FileSystem fs = FileSystem.get(new Configuration());
        String sep = ",";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fs.open(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(sep);
                BigInteger key = new BigInteger(record[0].replaceAll("\"", ""));
                String value = record[3].replaceAll("\"", "");
                geobase.put(key, value);
            }
        }
        return geobase;
    }
}
