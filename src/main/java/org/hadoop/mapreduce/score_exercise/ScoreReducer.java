package org.hadoop.mapreduce.score_exercise;

import com.google.common.collect.Sets;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Set;

public class ScoreReducer extends Reducer<Text,Text,Text, NullWritable> {
    private Text k = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String result = parseData(key, values);
        k.set(result);
        context.write(k,NullWritable.get());
    }
    //reduce解析数据业务
    //业务要求输出格式
    //课程\t甲级\t学生1,学生2,...\t总人数
    private String parseData(Text key,Iterable<Text> values){
        int sum=0;
        StringBuffer sb = new StringBuffer();

        String[] keyFields = key.toString().split(ScoreConst.KEY_SPLITTER);
        sb.append(keyFields[0]);
        sb.append(ScoreConst.DATA_SPLITTER_TAB);
        sb.append(ScoreConst.GRADE_MAP.get(keyFields[1]));
        sb.append(ScoreConst.DATA_SPLITTER_TAB);
        //同等级姓名升序 方便查看
        Set<String> nameSet = Sets.newTreeSet((n1,n2)->n1.compareTo(n2));
        for(Text v : values){
            String[] valueFields = v.toString().split(ScoreConst.VALUE_SPLITTER);
            String name =valueFields[0];
            nameSet.add(name);
            //业务虽然没用到但保留数据提高拓展性
//            int score = Integer.parseInt(valueFields[1]);
            sum+=1;
        }
        for(String name : nameSet){
            sb.append(name);
            sb.append(ScoreConst.DATA_SPLITTER_COMMA);
        }
        //去除多余的逗号
        sb = sb.deleteCharAt(sb.lastIndexOf(ScoreConst.DATA_SPLITTER_COMMA));
        sb.append(ScoreConst.DATA_SPLITTER_TAB);
        sb.append(sum);
        return sb.toString();
    }
}
