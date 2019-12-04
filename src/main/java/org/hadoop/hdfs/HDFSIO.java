package org.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HDFSIO {
    private FileSystem fs = HDFSFactory.getDefaultFileSystem();
    private Configuration conf = HDFSFactory.getDefaultConf();

    @Test
    public void get() throws Exception{
        FSDataInputStream fis = fs.open(new Path("/user/root/input/README.txt"));
        FileOutputStream fos = new FileOutputStream(new File("H:/test"));
        IOUtils.copyBytes(fis,fos,conf);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    @Test
    public void put() throws Exception{
        FileInputStream fis = new FileInputStream("H:/new/1.txt");
        FSDataOutputStream fos = fs.create(new Path("/user/root/upload/1.txt"));
        IOUtils.copyBytes(fis,fos,conf);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    @Test
    public void getblock0() throws Exception{
        FSDataInputStream fis = fs.open(new Path("/user/root/software/hadoop-2.7.2.tar.gz"));
        FileOutputStream fos = new FileOutputStream(new File("./download/hadoop-2.7.2.tar.gz.part1"));
//        IOUtils.copyBytes(fis,fos,conf);
        byte[] buf = new byte[1024];//缓冲区就是1M
        //读到128M
        for(int i=0;i<1024*128;i++){
            fis.read(buf);
            fos.write(buf);
        }
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
    @Test
    public void getblock1() throws Exception{
        FSDataInputStream fis = fs.open(new Path("/user/root/software/hadoop-2.7.2.tar.gz"));
        FileOutputStream fos = new FileOutputStream(new File("./download/hadoop-2.7.2.tar.gz.part2"));
        fis.seek(1024*1024*128);//第二块从128M开始读一直到末尾
        IOUtils.copyBytes(fis,fos,conf);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    @Test
    public void hflush() throws Exception{
        FSDataOutputStream fos = fs.create(new Path("/flush.txt"));
        fos.write("flush".getBytes());
        fos.hflush();
        IOUtils.closeStream(fos);
        fs.close();
        System.out.println("done");
    }
}
