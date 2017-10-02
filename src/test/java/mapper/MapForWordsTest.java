package mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MapForWordsTest {
    MapDriver<LongWritable, Text, IntWritable, Text> mapDriver;

    @Before
    public void setUp() throws Exception {
        MapForWords mapper = new MapForWords();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() throws IOException {
        Text word = new Text("LOOOL");
        mapDriver.withInput(new LongWritable(), new Text(
                word));
        mapDriver.withOutput(new IntWritable((-1)*word.getLength()), word);
        mapDriver.runTest();
    }


}
