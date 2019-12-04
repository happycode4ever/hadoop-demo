package org.hadoop.hdfs;

import java.net.URI;
import java.net.URISyntaxException;

public class HDFSConst {
    public static String DEFAULT_USER = "root";
    public static URI DEFAULT_NAMENODE_URI = null;
    static{
        try {
            DEFAULT_NAMENODE_URI = new URI(CoreSite.FS_DETAULTFS_VALUE);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
    public static class CoreSite{
        public static String FS_DETAULTFS_KEY="fs.defaultFS";
        public static String FS_DETAULTFS_VALUE = "hdfs://bigdata121:9000";
    }
}
