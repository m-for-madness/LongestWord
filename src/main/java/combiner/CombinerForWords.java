package combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinerForWords extends Reducer<IntWritable, Text, IntWritable, Text> {
    private IntWritable max;
    private List<Text> listOfWords;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        max = new IntWritable(0);
        listOfWords = new ArrayList<>();
    }

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> itr = values.iterator();
        Text txt = new Text(itr.next());
        if(txt.getLength()>max.get()){
            listOfWords.clear();
            listOfWords.add(txt);
            max.set(txt.getLength());
            while(itr.hasNext()){
                listOfWords.add(itr.next());
            }
        }
        else if(txt.getLength()==max.get()){
            listOfWords.add(txt);
            while(itr.hasNext()){
                listOfWords.add(itr.next());
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        IntWritable tmp = new IntWritable(0);
        for (Text t : listOfWords) {
            tmp.set((-1)*t.getLength());
            context.write(tmp, t);
        }
    }
}
