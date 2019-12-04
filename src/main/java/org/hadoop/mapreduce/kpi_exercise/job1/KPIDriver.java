package org.hadoop.mapreduce.kpi_exercise.job1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.hadoop.mapreduce.util.FileInputOutputFormatUtil;

import java.io.IOException;

public class KPIDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(KPIDriver.class);
        job.setMapperClass(KPIMapper.class);
        job.setReducerClass(KPIReducer.class);
        job.setMapOutputKeyClass(KPIBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(KPIBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputOutputFormatUtil.initInputOutput(job,"out/kpi/input/emp.txt","out/kpi/result");
        job.setGroupingComparatorClass(KPIGroupingComparator.class);
        job.waitForCompletion(true);
    }
}
