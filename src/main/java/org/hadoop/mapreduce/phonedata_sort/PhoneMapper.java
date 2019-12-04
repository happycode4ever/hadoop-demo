package org.hadoop.mapreduce.phonedata_sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PhoneMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
    private Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
//        fields[0] = PhoneUtil.generatePhone();
        k.set(fields[0]);
        long upflow = Long.parseLong(fields[fields.length-3]);
        long downflow = Long.parseLong(fields[fields.length-2]);
        //封装单行数据流量到当前行所属手机号
        FlowBean flowBean = FlowBean.builder().upflow(upflow).downflow(downflow).build();
        context.write(k,flowBean);
    }
}
