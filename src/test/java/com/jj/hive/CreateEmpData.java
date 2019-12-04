package com.jj.hive;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class CreateEmpData {

    public static void main(String[] args) throws Exception{
        int[] depts = {10,20,30};
        char c = 'A';
        FileWriter fw = new FileWriter("./emp_data.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        Random random = new Random();
        for(int i=1;i<=1000;i++){
            String name = "";
            for(int j=0;j<5;j++){
               char newc = (char) (c + random.nextInt(26));
               name +=newc;
            }
            bw.write(i+"\t"+name+"\t"+depts[random.nextInt(depts.length)]+"\t"+(1000+random.nextInt(9000)));
            bw.newLine();
        }
        bw.close();
        fw.close();

    }
    @Test
    public void test(){
        String src = new String("ab43a2c43d");
        System.out.println(src.replace("3","f"));
        System.out.println(src.replace('3','f'));
        System.out.println(src.replaceAll("\\d","f"));
        System.out.println(src.replaceAll("a","f"));
        System.out.println(src.replaceFirst("\\d","f"));
        System.out.println(src.replaceFirst("4","h"));
    }
}
