package reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReduceForWords extends Reducer<IntWritable, Text, IntWritable, Text> {
    private Boolean check;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        check = false;
    }

    public void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context con) throws IOException, InterruptedException {
        Iterator<Text> itr = values.iterator();
        while(itr.hasNext()&& (check==false)){
            con.write(key, itr.next());
        check=true;
        }
    }


}