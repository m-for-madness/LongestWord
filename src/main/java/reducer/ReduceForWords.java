package reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReduceForWords extends Reducer<IntWritable, Text, IntWritable, Text> {
    private IntWritable max_length;
    private Set<String> set;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        set = new HashSet<>();
        max_length = new IntWritable(0);
    }

    public void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context con) throws IOException, InterruptedException {
        if (max_length.get() == 0) {

            for (Text t : values) {
                set.add(t.toString());
                max_length.set(t.getLength());
            }
            System.out.println(set.size());
            String s = set.stream().collect(Collectors.joining("\n"));
            con.write(max_length, new Text(s));
        }
    }

}