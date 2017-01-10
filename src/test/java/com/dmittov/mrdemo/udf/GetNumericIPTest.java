package com.dmittov.mrdemo.udf;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by mittov on 10/01/2017.
 */
public class GetNumericIPTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void evaluate() throws Exception {
        GetNumericIP inst = new GetNumericIP();
        assertEquals(inst.evaluate(new Text("0.0.0.32")), new LongWritable(32l));
        assertEquals(inst.evaluate(new Text("127.0.0.1")), new LongWritable(2130706433l));
        assertEquals(inst.evaluate(new Text("255.255.255.0")), new LongWritable(4294967040l));
        exception.expect(NumberFormatException.class);
        inst.evaluate(new Text("2a02:6b8::40c:18c0:f311:d05f:b3d"));
    }

}