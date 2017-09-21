package mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class MapForWordsTest {
    MapDriver<LongWritable, Text, IntWritable, Text> mapDriver;

    @Before
    public void setUp() throws Exception {
        MapForWords mapper = new MapForWords();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() {
        Text word = new Text("loool");
        mapDriver.withInput(new LongWritable(), new Text(
                word));
        mapDriver.withOutput(new IntWritable((-1)*word.getLength()), word);
    }


}
