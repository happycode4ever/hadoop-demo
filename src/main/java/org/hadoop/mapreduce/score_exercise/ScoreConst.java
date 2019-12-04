package org.hadoop.mapreduce.score_exercise;

import com.google.common.collect.Maps;

import java.util.Map;

public class ScoreConst {
    /**
     * 分隔符
     */
    public static String DATA_SPLITTER_COMMA = ",";
    public static String DATA_SPLITTER_TAB = "\t";
    public static String KEY_SPLITTER = "\001";
    public static String VALUE_SPLITTER = "\002";
    /**
     * 等级
     */
    public static String GRADE_A = "A";
    public static String GRADE_B = "B";
    public static String GRADE_C = "C";
    public static Map<String,String> GRADE_MAP =Maps.newHashMap();
    static{
        GRADE_MAP.put(GRADE_A,"甲级");
        GRADE_MAP.put(GRADE_B,"乙级");
        GRADE_MAP.put(GRADE_C,"丙级");
    }
}
