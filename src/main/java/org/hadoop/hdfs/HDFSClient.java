package org.hadoop.hdfs;

import org.apache.hadoop.fs.*;
import org.junit.Test;

public class HDFSClient {
    private FileSystem fs = HDFSFactory.getDefaultFileSystem();

    @Test
    public void copyFromLocal() throws Exception {
        fs.copyFromLocalFile(new Path("H:\\bigdata-dev\\ideaworkspace\\hdfsclient"),new Path("/"));
        fs.close();
        System.out.println("done");
    }
    @Test
    public void download() throws Exception{
        fs.copyToLocalFile(false,new Path("/hdfs"),new Path("H:/"),true);
        fs.close();
        System.out.println("done");
    }

    @Test
    public void createdirs() throws Exception{
        fs.mkdirs(new Path("/aaa/bbb/ccc"));
        fs.close();
        System.out.println("done");
    }

    @Test
    public void delrecursive() throws Exception{
        fs.delete(new Path("/aaa"),true);
        fs.close();
        System.out.println("done");
    }

    @Test
    public void rename() throws Exception{
        fs.rename(new Path("/user/root/upload/1.txt"), new Path("/test2.txt"));
        fs.close();
        System.out.println("done");
    }

    @Test
    public void readFileList() throws Exception{
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);

        while(iterator.hasNext()){
            LocatedFileStatus status = iterator.next();
            System.out.println(status.getPath().getName());
            System.out.println(status.getPermission());
            System.out.println(status.getLen());
            System.out.println(status.getOwner());
            System.out.println(status.getGroup());
            BlockLocation[] blocks = status.getBlockLocations();
            for(BlockLocation block :blocks){
                System.out.println("offset:"+block.getOffset());
                for(String host : block.getHosts()) System.out.println(host);
            }
    }
        fs.close();
        System.out.println("done");
    }

    @Test
    public void whetherFile() throws Exception{
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus status : fileStatuses) {
            if(status.isFile()){
                System.out.println("-:"+status.getPath().getName());
        }else{
                System.out.println("d:"+status.getPath().getName());
            }
        }
    }
}
