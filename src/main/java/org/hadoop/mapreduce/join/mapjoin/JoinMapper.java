package org.hadoop.mapreduce.join.mapjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    //pid	pname
    //01	小米
    //02	华为
    private Map<String,String> productMap = new HashMap<>();
    private Text k = new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheURIs = context.getCacheFiles();
        BufferedReader br = new BufferedReader(new FileReader(new File(cacheURIs[0])));
        String line=null;
        while((line=br.readLine())!=null){
            String[] fields = line.split("\t");
            productMap.put(fields[0],fields[1]);
        }
        br.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //id	pid	amount
        //1001	01	1
        //1002	02	2
        String[] fields = value.toString().split("\t");
        StringBuffer sb = new StringBuffer();
        sb.append(fields[0]+"\t");
        sb.append(fields[1]+"\t");
        sb.append(productMap.get(fields[1])+"\t");
        sb.append(fields[2]);
        k.set(sb.toString());
        context.write(k,NullWritable.get());
    }
}
