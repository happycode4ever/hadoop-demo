package org.hadoop.mapreduce.kpi_exercise.job1;

import com.google.common.collect.Maps;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class KPIMapper extends Mapper<LongWritable, Text,KPIBean, NullWritable> {
    //需求背景：
    //公司有100万员工 100个部门 绩效评分1-100分
    // 全公司前10%等级A 之后20%等级B 之后20%等级C 之后40%等级D 最后10%等级E
    //等级A奖金为该员工等级月薪*4 等级A奖金为该员工等级月薪*3 等级A奖金为该员工等级月薪*2 等级A奖金为该员工等级月薪*1 等级E奖金为0
    //基本需求
    //1.要求输出全公司所有员工的KPI等级 先按部门升序，再按部门内等级降序，同等级按姓名升序
    //2.要求按部门输出所有员工的KPI等级 每个部门内等级降序 姓名升序
    //**进阶需求1 经理除了普通员工奖金还有额外奖金 按照当前部门平均个人绩效评分在所有部门中的排名/100*1*当前经理等级月薪
    //**进阶需求2 要求输出员工绩效在全公司排名以及当前部门排名，如果分数相同，名次相同

    //数据准备
    //员工表emp
    //员工id 姓名 所属部门id 员工等级 绩效评分
    //部门表dept
    //部门id 部门名称 (经理员工id)
    //薪资表sal
    //员工等级 薪资

    //要求输出数据
    //员工id 员工姓名 所属部门id 所属部门名称 经理名称 绩效评分 奖金
    //进阶需求要求输出数据
    //如果是经理需要列出额外奖金，全公司绩效排名，当前部门绩效排名

private Map<String,String> deptMap;
private Map<String,String> salMap;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        deptMap= Maps.newHashMap();
        salMap=Maps.newHashMap();
        BufferedReader br = new BufferedReader(new FileReader("out/kpi/input/dept.txt"));
        String line=null;
        while((line=br.readLine())!=null){
            String[] fields = line.split("\t");
            deptMap.put(fields[0],fields[1]);
        }BufferedReader br2 = new BufferedReader(new FileReader("out/kpi/input/sal.txt"));
        String line2=null;
        while((line2=br2.readLine())!=null){
            String[] fields = line2.split("\t");
            salMap.put(fields[0],fields[1]);
        }
        br2.close();
        br.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        String empId = fields[0];
        String ename = fields[1];
        String deptId = fields[2];
        String grade = fields[3];
        String score = fields[4];
        KPIBean bean = KPIBean.builder()
                .empId(empId)
                .ename(ename)
                .deptId(deptId)
                .dname(deptMap.get(deptId))
                .grade(grade)
                .sal(Integer.parseInt(salMap.get(grade)))
                .kpi("")
                .score(Integer.parseInt(score)).build();
        context.getCounter("KPIMapper","bean").increment(1);
        context.write(bean,NullWritable.get());
    }
}
