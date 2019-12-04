package org.hadoop.mapreduce.phonedata_sort;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

public class PhoneUtil {
    private PhoneUtil(){}
    //中国电信号段
    //133、149、153、173、177、180、181、189、199
    //中国联通号段
    //130、131、132、145、155、156、166、171、175、176、185、186、166
    //中国移动号段
    //134、135、136、137、138、139、147、150、151、152、157、158、159、172、178、182、183、184、187、188、198

    public static List<String> DX_PREFIX_LIST = Lists.newArrayList("133","149","153","173","177","180","181","189","199");
    public static List<String> LT_PREFIX_LIST = Lists.newArrayList("130","131","132","145","155","156","166","171","175","176","185","186","166");
    public static List<String> YD_PREFIX_LIST = Lists.newArrayList("134","135","136","137","138","139","147","150","151","152","157","158","159","172","178","182","183","184","187","188","198");

    public static String generatePhone(){
        Random random = new Random();
        int type = random.nextInt(3);
        StringBuffer sb = new StringBuffer();
        String prefix = "";
        //随机从列表中抽取前三位
        switch (type){
            case 0:
                prefix = DX_PREFIX_LIST.get(random.nextInt(DX_PREFIX_LIST.size()));break;
            case 1:
                prefix = LT_PREFIX_LIST.get(random.nextInt(LT_PREFIX_LIST.size()));break;
            case 2:
                prefix = YD_PREFIX_LIST.get(random.nextInt(YD_PREFIX_LIST.size()));break;
        }
        sb.append(prefix);
        //随机生成后8位
        for(int i=0;i<8;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
