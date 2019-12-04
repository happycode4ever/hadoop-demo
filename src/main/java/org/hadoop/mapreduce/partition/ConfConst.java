package org.hadoop.mapreduce.partition;

public class ConfConst {
    public static String OUTPUT_PATH_KEY="output";
    public static String OUTPUT_PATH_HDFS_PREFIX="/user/root/output/";
    public static String OUTPUT_PATH_LOCAL_PREFIX="H:/output/";
    public static String OUTPUT_PATH_ODD_FILE="oddLengthWord.txt";
    public static String OUTPUT_PATH_EVEN_FILE="evenLengthWord.txt";
    public static String OUTPUT_PATH_HDFS_VALUE=OUTPUT_PATH_HDFS_PREFIX+OUTPUT_PATH_ODD_FILE+","+OUTPUT_PATH_HDFS_PREFIX+OUTPUT_PATH_EVEN_FILE;
    public static String OUTPUT_PATH_LOCAL_VALUE=OUTPUT_PATH_LOCAL_PREFIX+OUTPUT_PATH_ODD_FILE+","+OUTPUT_PATH_LOCAL_PREFIX+OUTPUT_PATH_EVEN_FILE;
}
