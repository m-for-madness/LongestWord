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
    private Text txt;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        max = new IntWritable(0);
        listOfWords = new ArrayList<>();
        txt = new Text("");
    }

    @Override
    public void reduce(IntWritable key, Iterable<Text> values, Reducer<IntWritable, Text, IntWritable, Text>.Context con) throws IOException, InterruptedException {
        Iterator<Text> itr = values.iterator();
        Text txt;
        while (itr.hasNext()) {
            txt = new Text(itr.next());
            if (txt.getLength() == max.get()) {
                listOfWords.add(txt);
            }
            if (txt.getLength() > max.get()) {
                max.set(txt.getLength());
                listOfWords.clear();
                listOfWords.add(txt);
            }
        }
        for(Text t : listOfWords){
            con.write(new IntWritable(max.get()*-1),t);
        }
    }

}
