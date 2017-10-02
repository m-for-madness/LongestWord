package reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceForWords extends Reducer<IntWritable, Text, IntWritable, Text> {
    private IntWritable max_length;
    private List<String> list;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        list = new ArrayList<>();
        max_length = new IntWritable(0);
    }

    public void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context con) throws IOException, InterruptedException {
        if (max_length.get() == 0) {

            for (Text t : values) {
                list.add(t.toString());
                max_length.set(t.getLength());
            }
            String s = list.stream().collect(Collectors.joining(" , "));
            con.write(max_length, new Text(s));
        }
    }

}