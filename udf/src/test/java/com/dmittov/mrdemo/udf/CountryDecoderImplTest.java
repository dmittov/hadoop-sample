package com.dmittov.mrdemo.udf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by mittov on 13/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestUDFContext.class)
public class CountryDecoderImplTest {

    @Autowired
    private CountryDecoder countryDecoder;

    @Test
    public void getCountry() throws Exception {
        String actual = this.countryDecoder.getCountry("37.110.157.153");
        assertEquals("Russian Federation", actual);
    }
}