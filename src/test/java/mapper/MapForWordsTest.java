package mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class MapForWordsTest {
    MapDriver<LongWritable, Text, IntWritable, Text> mapDriver;

    @Before
    public void setUp() throws Exception {
        MapForWords mapper = new MapForWords();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() {
        mapDriver.withInput(new LongWritable(), new Text(
                "lol loool lol lool "));
        mapDriver.withOutput(new IntWritable(5), new Text("loool"));
        mapDriver.runTest();
    }


}
