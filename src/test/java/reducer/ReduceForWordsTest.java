package reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReduceForWordsTest {
    ReduceDriver<IntWritable, Text, IntWritable, Text> reduceDriver;

    @Before
    public void setUp() throws Exception {
        ReduceForWords reduceForWords = new ReduceForWords();
        reduceDriver = ReduceDriver.newReduceDriver(reduceForWords);
    }

    @Test
    public void reduceTest() throws Exception {
        List<Text> values = new ArrayList<>();
        values.add(new Text("tedt"));

        reduceDriver.withInput(new IntWritable(4), values);
        reduceDriver.withOutput(new IntWritable(4), new Text("tedt"));
        reduceDriver.runTest();
    }
}
