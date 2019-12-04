package org.hadoop.mapreduce.phonedata_sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PhonePartitoner extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
        String prefix = text.toString().substring(0, 3);
        if(PhoneUtil.DX_PREFIX_LIST.contains(prefix))return 0;
        if(PhoneUtil.LT_PREFIX_LIST.contains(prefix))return 1;
        if(PhoneUtil.YD_PREFIX_LIST.contains(prefix))return 2;
        return 3;
    }
}
