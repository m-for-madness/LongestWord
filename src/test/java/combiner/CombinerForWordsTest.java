package combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import reducer.ReduceForWords;

import java.util.ArrayList;
import java.util.List;

public class CombinerForWordsTest {

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
        values.add(new Text("ted1"));
        values.add(new Text("ted2"));
        values.add(new Text("ted3"));

      //  reduceDriver.withInput(new IntWritable(4), values);
      //  reduceDriver.withOutput(new IntWritable(4), new Text("tedt"));
       // reduceDriver.runTest();
        new ReduceDriver<IntWritable, Text, IntWritable, Text>().withInput(new IntWritable(4), values).withOutput( new IntWritable(4), values.get(0))
                                                                                                            .withOutput( new IntWritable(4), values.get(1))
                                                                                                            .withOutput(new IntWritable(4), values.get(2))
                                                                                                            .withOutput(new IntWritable(4), values.get(3));
    }
}
