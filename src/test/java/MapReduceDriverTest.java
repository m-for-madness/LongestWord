import mapper.MapForWords;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;
import reducer.ReduceForWords;

import java.util.ArrayList;
import java.util.List;

public class MapReduceDriverTest {
    MapReduceDriver<LongWritable, Text, IntWritable, Text, IntWritable, Text> mapReduceDriver;
    MapForWords mapper;
    ReduceForWords reducer;
    @Before
    public void setUp() throws Exception {
        mapper = new MapForWords();
        reducer = new ReduceForWords();
        mapReduceDriver = mapReduceDriver.newMapReduceDriver(mapper,reducer);
    }

    @Test
    public void mapreduceTest() throws Exception {
        mapReduceDriver.withInput(new LongWritable(), new Text("bok;wiw;wooow;loooool;kek"));
        List<Text> list = new ArrayList<>();
        list.add(new Text("bok"));
        list.add(new Text("wiw"));
        list.add(new Text("wooow"));
        list.add(new Text("loooool"));
        list.add(new Text("kek"));
        mapReduceDriver.withOutput(new IntWritable(7), new Text("LOOOOOL"));
        mapReduceDriver.runTest();

    }
}
