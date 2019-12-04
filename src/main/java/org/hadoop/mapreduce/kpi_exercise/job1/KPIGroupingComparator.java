package org.hadoop.mapreduce.kpi_exercise.job1;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KPIGroupingComparator extends WritableComparator {
    public KPIGroupingComparator(){
        super(KPIBean.class,true);
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        KPIBean kb1 = (KPIBean) a;
        KPIBean kb2 = (KPIBean) b;
        return kb1.getEmpId().compareTo(kb2.getEmpId());
    }
}
