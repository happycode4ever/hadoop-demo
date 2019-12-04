package org.hadoop.mapreduce.join.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.hadoop.mapreduce.util.FileInputOutputFormatUtil;

import java.net.URI;

public class JoinDriver {
    public static void main(String[] args) throws Exception{
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(JoinDriver.class);
        job.setMapperClass(JoinMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);
        job.addCacheFile(new URI("file:///H:/bigdata-dev/tools/hadoop-data/mr-join/input/pd.txt"));
        FileInputOutputFormatUtil.initInputOutput(job,args[0],args[1]);
        job.waitForCompletion(true);
    }
}
