package org.hadoop.mapreduce.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.hadoop.mapreduce.util.FileInputOutputFormatUtil;

import java.io.IOException;

public class WordCountDriver implements Tool {
    private Configuration configuration;

    public static void main(String[] args) throws Exception{
        int res = ToolRunner.run(new WordCountDriver(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {
//        configuration.set(ConfConst.OUTPUT_PATH_KEY,ConfConst.OUTPUT_PATH_LOCAL_VALUE);
        Job job = Job.getInstance(configuration);
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setPartitionerClass(CustomPartitoner.class);
        job.setNumReduceTasks(2);
//        job.setOutputFormatClass(CustomOutputFormat.class);
        MultipleOutputs.addNamedOutput(job,"odd", TextOutputFormat.class,Text.class,IntWritable.class);
        MultipleOutputs.addNamedOutput(job,"even", TextOutputFormat.class,Text.class,IntWritable.class);
        FileInputOutputFormatUtil.initInputOutput(job,args[0],args[1]);
        return job.waitForCompletion(true)?1:-1;

    }



    @Override
    public void setConf(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public Configuration getConf() {
        return configuration;
    }
}
