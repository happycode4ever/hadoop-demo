package org.hadoop.mapreduce.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FileInputOutputFormatUtil {
    private FileInputOutputFormatUtil(){}
    public static void initInputOutput(Job job, String inpath, String outpath) throws IOException {
        FileInputFormat.setInputPaths(job,new Path(inpath));
        Configuration conf = job.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        Path outPath = new Path(outpath);
        if(fs.exists(outPath)){
            fs.delete(outPath,true);
        }
        FileOutputFormat.setOutputPath(job,outPath);
    }
}
