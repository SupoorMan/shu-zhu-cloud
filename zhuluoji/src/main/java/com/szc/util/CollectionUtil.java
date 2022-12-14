package com.szc.util;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 数据集合工具类
 */
public class CollectionUtil {

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(@Nullable Object[] arr) {
        return (arr == null || arr.length < 1);
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }


    public static <T> boolean contain(T[] arr, T val) {
        for (T value : arr) {
            if (value.equals(val)) {
                return true;
            }
        }

        return false;
    }

}
