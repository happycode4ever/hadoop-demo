package org.hadoop.mapreduce.score_exercise;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ScoreMapper extends Mapper<LongWritable, Text,Text,Text> {
    private Text k = new Text();
    private Text v = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(ScoreConst.DATA_SPLITTER_COMMA);
        String subject = fields[0];
        String name = fields[1];
        int score = Integer.parseInt(fields[2]);
        String grade = parseScore(score);
        if(isValidData(subject,name,score)){
            //key按科目和等级分组
            k.set(subject+ScoreConst.KEY_SPLITTER+grade);
            v.set(name+ScoreConst.VALUE_SPLITTER+score);
        }
        context.write(k,v);
    }
    //数据校验
    private boolean isValidData(String subject,String name,int score){
        if(StringUtils.isEmpty(subject)||StringUtils.isEmpty(name)||StringUtils.isEmpty(parseScore(score))){
            return false;
        }
        return true;
    }

    //解析成绩业务
    private String parseScore(int score){
        if(score>=90)return ScoreConst.GRADE_A;
        if(score>=80)return ScoreConst.GRADE_B;
        if(score>=0)return ScoreConst.GRADE_C;
        return null;
    }
}
