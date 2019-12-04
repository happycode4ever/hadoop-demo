package org.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WordcountMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //CombineTextInputFormat合并了切片，所以使用的切片类是CombineFileSplit，获取到的路径是一次过加载后的路径
//        CombineFileSplit fs = (CombineFileSplit) context.getInputSplit();
//        System.out.println(fs.getPaths());
        String[] fields = value.toString().split("\t");
        for(String f : fields){
            k.set(f);
            context.write(k, NullWritable.get());
        }
    }
}
