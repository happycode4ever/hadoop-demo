package org.hadoop.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

import static org.hadoop.hdfs.HDFSConst.*;

public class HDFSFactory {
    private HDFSFactory(){}
    private static Configuration conf = new Configuration();
    static{
        conf.set(CoreSite.FS_DETAULTFS_KEY,CoreSite.FS_DETAULTFS_VALUE);
    }

    public static Configuration getDefaultConf(){
        return conf;
    }

    public static FileSystem getDefaultFileSystem(){
        FileSystem fs = null;
        try {
            fs = FileSystem.get(DEFAULT_NAMENODE_URI,conf,DEFAULT_USER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fs;
    }
}
