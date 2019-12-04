package org.hadoop.mapreduce.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class CustomRecordWriter extends RecordWriter<Text, IntWritable> {

    private FSDataOutputStream oddfos = null;
    private FSDataOutputStream evenfos = null;
    private FileSystem fs = null;

    public CustomRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException {
        Configuration conf = taskAttemptContext.getConfiguration();
        fs = FileSystem.get(conf);
//        String[] paths = conf.get(ConfConst.OUTPUT_PATH_KEY).split(",");
//        for(String path : paths){
//            if(path.contains(ConfConst.OUTPUT_PATH_ODD_FILE)){
//                oddfos = fs.create(new Path(path));
//            }
//            if(path.contains(ConfConst.OUTPUT_PATH_EVEN_FILE)){
//                evenfos = fs.create(new Path(path));
//            }
//        }
        oddfos = fs.create(new Path("H:/output/odd.txt"));
        evenfos = fs.create(new Path("H:/output/even.txt"));
        System.out.println("CustomRecordWriter 初始化完成,oddfos:"+oddfos+",evenfos:"+evenfos);
    }

    @Override
    public synchronized void write(Text text, IntWritable intWritable) throws IOException, InterruptedException {
        int length = text.toString().length();
        String result = text.toString()+"\t"+intWritable.get()+"\n";
        if(length%2==0){
            evenfos.write(result.getBytes());
            evenfos.hflush();
        }else{
            oddfos.write(result.getBytes());
            oddfos.hflush();
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if(oddfos!=null)oddfos.close();
        if(evenfos!=null)evenfos.close();
        if(fs!=null)fs.close();
    }
}
