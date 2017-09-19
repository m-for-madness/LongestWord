package reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ReduceForWords extends Reducer<IntWritable, Text, IntWritable,Text> {
     private List<Text> listOfWords = new ArrayList<>();
     private IntWritable max_length = new IntWritable(0);
    public void reduce(IntWritable key, Iterable<Text> values,  Reducer<IntWritable, Text, IntWritable,Text>.Context con) throws IOException, InterruptedException {
        Iterator<Text> itr = values.iterator();
        Text txt;
        while(itr.hasNext()){
            txt = new Text(itr.next());
            if(txt.getLength()>max_length.get()){
                listOfWords.clear();
                max_length.set(txt.getLength());
                listOfWords.add(txt);
            }
            else  if (txt.getLength()==max_length.get()){
                listOfWords.add(txt);
            }
        }


    }

    @Override
    protected void cleanup(Context con) throws IOException, InterruptedException {
        IntWritable tmp = new IntWritable(0);
        for(Text t : listOfWords){
            tmp.set(t.getLength());
            con.write(tmp, t);
        }

    }
}