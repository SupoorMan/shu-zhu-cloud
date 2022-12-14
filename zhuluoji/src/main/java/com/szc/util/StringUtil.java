package com.szc.util;

import java.util.Random;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String generateUid() {
        long millis = System.currentTimeMillis() / 1000;
        return getStringRandom(2) + millis + getStringRandom(6);
    }

    /**
     * 生成随机数
     *
     * @param num 随机位数
     * @return 0-9组合随机数
     */
    public static String getStringRandom(int num) {
        Random random = new Random();
        //把随机生成的数字转成字符串
        String str = String.valueOf(random.nextInt(9));
        for (int i = 0; i < num - 1; i++) {
            str += random.nextInt(9);
        }
        return str;
    }

}
