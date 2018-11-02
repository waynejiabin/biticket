package com.ruoyi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 *
 * 主键生成策略类
 * 规则:年月日时分秒 + 随机的 4 位
 *
 */

public class MajorKeyUtil {
    private static Logger logger = LoggerFactory.getLogger(MajorKeyUtil.class);

    /**
     * 随机生成6位数
     * @return
     */
    public  static String createRandomSix(){
        Random random = new Random();
        String randomNum =createRandom(6);
        return randomNum;
    }

    /**
     * 随机生成指定位数的随机数
     * @return
     */
    public  static String createRandom(int len){
        Random random = new Random();
        String fourRandom = random.nextInt(1000000) + "";
        int randLength = fourRandom.length();
        if(randLength<len){
            for(int i=1; i<=len-randLength; i++)
            {
                fourRandom = "0" + fourRandom  ;
            }
        }
        return fourRandom;
    }
    /**
     * 生成主键
     * @return
     */
    public static String  createMajorKey(){
        return   removeRepeat();
    }
    /**
     * 生成主键
     * @param prefix  指定的前缀
     * @return
     */
    public static String  createMajorKey(String prefix){
        return removeRepeat(prefix);
    }

    public  static String removeRepeat(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(System.currentTimeMillis());
        buffer.append(createRandomSix());
        return buffer.toString() ;
    }

    /**
     *
     * @param prefix 前缀
     * @return
     */
    public  static String removeRepeat(String prefix){
        StringBuffer buffer = new StringBuffer();
        if(null!=prefix && ! "".equals(prefix))
        {
            buffer.append(prefix);
        }
        buffer.append(System.currentTimeMillis());
        buffer.append(createRandomSix());
        return buffer.toString() ;
    }

}
