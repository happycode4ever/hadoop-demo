package org.hadoop.mapreduce.kpi_exercise;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class GenerateUtil {

    @Test
    public void createDept() throws IOException {
        int deptNum = 100;
        BufferedWriter bw = new BufferedWriter(new FileWriter("out/kpi/input/dept.txt"));
        char A = 'A';
        int j=1;
        for(int i=1;i<=deptNum;i++){
            StringBuffer sb = new StringBuffer();
            sb.append(i);
            sb.append("\t");
            sb.append("dept");
            //25*4
            char res1 = (char) (A+(i-1)/4);
            if(j==5){
                j=1;
            }
            sb.append(res1);
            sb.append(j++);
            bw.write(sb.toString());
            if(i!=deptNum)
            bw.newLine();
        }
        bw.close();
    }
    @Test
    public void createSal() throws IOException {
        int salNum = 10;
        int startSal = 10000;
        BufferedWriter bw = new BufferedWriter(new FileWriter("out/kpi/input/sal.txt"));
        for(int i=1;i<=salNum;i++){
            bw.write("p"+i+"\t"+startSal*i);
            if(i!=salNum)
            bw.newLine();
        }
        bw.close();
    }

    @Test
    public void createEmp() throws IOException {
        int empNum = 100;
        //员工id 姓名 所属部门id 员工等级 绩效评分
        BufferedWriter bw = new BufferedWriter(new FileWriter("out/kpi/input/emp.txt"));
        Random random = new Random();
        char s1 = 'A';
        int s2 = 1;
        for(int i=1;i<=empNum;i++){
            StringBuffer sb = new StringBuffer();
            sb.append(i);
            sb.append("\t");
            //生成姓名
            String uuid = UUID.randomUUID().toString();
            String timestamp = String.valueOf(System.currentTimeMillis()+random.nextInt(10000));

            sb.append(uuid.substring(uuid.length()-5,uuid.length())+timestamp.substring(timestamp.length()-5,timestamp.length()));
            sb.append("\t");
            //生成部门id
            sb.append(random.nextInt(100)+1);
            sb.append("\t");
            //生成等级
            sb.append("p"+(random.nextInt(10)+1));
            sb.append("\t");
            //生成分数
            sb.append(random.nextInt(100)+1);
            bw.write(sb.toString());
            if(i!=empNum)
            bw.newLine();
        }
        bw.close();
    }

    @Test
    public void test(){
        System.out.println(UUID.randomUUID());
        System.out.println(System.currentTimeMillis());
    }
}
