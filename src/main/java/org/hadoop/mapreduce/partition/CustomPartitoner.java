package org.hadoop.mapreduce.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitoner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        int length = text.toString().length();
        if(length%2==0){
            return 0;
        }
        return 1;
    }
}
