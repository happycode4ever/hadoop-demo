package org.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, NullWritable, Text, IntWritable> {
    private IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for(NullWritable n : values){
            sum+=1;
        }
        v.set(sum);
        context.write(key,v);
    }
}
