package mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapForWords extends Mapper<LongWritable, Text, IntWritable, Text> {
    public void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context con) throws IOException, InterruptedException
    {
        String line = value.toString();
        String[] words = line.split("[ ,.!?()*\":;/']");
        for (String word : words) {
            IntWritable outputKey = new IntWritable((-1)*word.length());
            Text outputValue = new Text(word.trim());
            con.write(outputKey, outputValue);
        }

    }

}
