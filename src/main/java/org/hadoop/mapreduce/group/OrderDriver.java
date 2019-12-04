package org.hadoop.mapreduce.group;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.hadoop.mapreduce.util.FileInputOutputFormatUtil;

public class OrderDriver {
    public static void main(String[] args) throws Exception{
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(OrderDriver.class);
        job.setMapperClass(OrderMapper.class);
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setReducerClass(OrderReducer.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        //设定分组类
        job.setGroupingComparatorClass(OrderGroupComparator.class);
        FileInputOutputFormatUtil.initInputOutput(job,args[0],args[1]);
        job.waitForCompletion(true);
    }
}
