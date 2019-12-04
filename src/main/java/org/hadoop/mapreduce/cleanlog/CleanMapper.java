package org.hadoop.mapreduce.cleanlog;

import com.google.common.base.Splitter;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Objects;

public class CleanMapper extends Mapper<LongWritable, Text, NullWritable,Text> {
    private Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        LogBean logBean = parseLog(value.toString());
        if(Objects.nonNull(logBean)){
            v.set(logBean.toString());
            context.getCounter("parseLog.result","true").increment(1);
            context.write(NullWritable.get(),v);
        }else{
            context.getCounter("parseLog.result","false").increment(1);
        }
    }
    private LogBean parseLog(String line){
        String[] fields = line.split(" ");
        if(fields.length>11) {
            //封装数据
            LogBean logBean = LogBean.builder()
                    .remote_addr(fields[0])
                    .remote_user(fields[1])
                    .time_local(fields[3] + fields[4])
                    .request(fields[7])
                    .status(fields[8])
                    .body_bytes_sent(fields[9])
                    .http_referer(fields[10])
                    .build();
            if (fields.length > 12) {
                logBean.setHttp_user_agent(fields[11] + " " + fields[12]);
            } else {
                logBean.setHttp_user_agent(fields[11]);
            }
            if (logBean.isValid()) {
                return logBean;
            }
        }
        return null;
    }
}
