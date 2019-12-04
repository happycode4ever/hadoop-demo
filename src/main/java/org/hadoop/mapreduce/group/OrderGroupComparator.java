package org.hadoop.mapreduce.group;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupComparator extends WritableComparator {
    public OrderGroupComparator(){
        //父构造器通过反射构建key所属对象
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean o1 = (OrderBean) a;
        OrderBean o2 = (OrderBean) b;
        //相同订单认为是同一组
        return o1.getOrderId().compareTo(o2.getOrderId());
    }
}
