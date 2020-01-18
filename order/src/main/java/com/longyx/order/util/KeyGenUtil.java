package com.longyx.order.util;

import java.util.Random;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:34
 */
public class KeyGenUtil {
    /**
     * 生成唯一的主见
     * 格式：时间+随机数
     * @author Mr.Longyx
     * @date 2020/1/12 1:00
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+ String.valueOf(number);
    }
}

