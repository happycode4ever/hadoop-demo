package org.hadoop.mapreduce.kpi_exercise.job1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.math.BigDecimal;

public class KPIReducer extends Reducer<KPIBean, NullWritable,KPIBean,NullWritable> {
    //上次分数临时变量
    int lastScore = -1;
    //排名计数器
    int rank = 0;
    @Override
    protected void reduce(KPIBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for(NullWritable n : values){
            System.out.println(key);
        }
        int score = key.getScore();
        //如果当前分数不等于上次分数自增排名，更新上次分数临时变量
        if(score!=lastScore) {
            rank++;
            lastScore=score;
        }
        key.setTotalRank(rank);
        key.setKpi(parseKPI(rank,100));
        context.getCounter("KPIReducer","bean").increment(1);
        context.write(key,NullWritable.get());
    }

    private String parseKPI(int rank,int sum){
        double res = BigDecimal.valueOf(rank).divide(BigDecimal.valueOf(sum)).doubleValue();
        if(res<=0.1)return "A";
        if(res<=0.3)return "B";
        if(res<=0.5)return "C";
        if(res<=0.9)return "D";
        return "E";
    }
}
