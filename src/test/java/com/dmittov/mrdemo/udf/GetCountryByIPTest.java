package com.dmittov.mrdemo.udf;

import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mittov on 10/01/2017.
 */
public class GetCountryByIPTest {

    @Test
    public void evaluate() throws Exception {
        GetCountryByIP inst = new GetCountryByIP();
        Path currentRelativePath = Paths.get("");
        Path currentAbsPath = currentRelativePath.toAbsolutePath();
        Map<Long, String> geobase = inst.loadGeobase("file://" + currentAbsPath.toString() +
                "/docker-hive/provision/IP2LOCATION-LITE-DB1.CSV");
        inst.setGeobase(geobase);
        assertEquals(inst.evaluate(new Text("37.110.157.153")), new Text("Russian Federation"));
    }

}