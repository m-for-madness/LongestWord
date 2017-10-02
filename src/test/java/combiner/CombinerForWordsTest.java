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
        CombinerForWords reduceForWords = new CombinerForWords();
        reduceDriver = ReduceDriver.newReduceDriver(reduceForWords);
    }

    @Test
    public void reduceTest() throws Exception {
        List<Text> values = new ArrayList<>();
        values.add(new Text("tedt23"));
        values.add(new Text("tedt56"));

        //reduceDriver.withInput(new IntWritable(6), values);
        //reduceDriver.withOutput( new IntWritable(values.get(0).getLength()*-1), values.get(0));
        reduceDriver.withInput(new IntWritable(-6), values).withOutput( new IntWritable(values.get(0).getLength()*-1), values.get(0))
                .withOutput( new IntWritable(values.get(1).getLength()*-1), values.get(1));
        reduceDriver.runTest();



    }
}
