package com.dmittov.mrdemo.udf;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mittov on 13/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestUDFContext.class)
public class PathGeobaseLoaderTest {

    private @Value("#{geobaseLoaderIPv4}") GeobaseLoader geobaseLoaderIPv4;
    private @Value("#{geobaseLoaderIPv6}") GeobaseLoader geobaseLoaderIPv6;

    @Test
    public void loadGeoBaseIPv4() throws Exception {
        assertEquals(141822, geobaseLoaderIPv4.loadGeoBase().size());
    }

    @Test
    public void loadGeoBaseIPv6() throws Exception {
        assertEquals(214262, geobaseLoaderIPv6.loadGeoBase().size());
    }
}