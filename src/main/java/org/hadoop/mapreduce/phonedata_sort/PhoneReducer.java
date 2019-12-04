package org.hadoop.mapreduce.phonedata_sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PhoneReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long upflow=0,downflow=0,sumflow=0;
        //按手机号分组，汇总所有流量
        for(FlowBean fb : values){
            upflow+=fb.getUpflow();
            downflow+=fb.getDownflow();
        }
        FlowBean flowBean = new FlowBean(upflow, downflow);
        context.write(key,flowBean);
    }

}
