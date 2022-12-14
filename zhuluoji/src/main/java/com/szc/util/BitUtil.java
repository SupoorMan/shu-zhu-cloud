package com.szc.util;

import java.util.BitSet;

public class BitUtil {

    public static BitSet convertRedisBitmapToJava(byte[] redisBitmapData) {
        int len = redisBitmapData.length;
        BitSet bitSet = new BitSet();
        // 每个 byte 8位, 所以整个bitmap 的长度为 len * 8
        for (int i = 0; i < len * 8; i++) {
            byte currentSegment = redisBitmapData[i / 8];
            if (currentSegment == 0) {
                continue;
            }
            if ((currentSegment & (1 << (7 - (i % 8)))) != 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }


    public static byte[] genRedisBitmap(int... items) {
        BitSet bitSet = new BitSet();
        // 2 55 133
        for (int k : items) {
            bitSet.set(k);
        }
        byte[] targetBitmap = bitSet.toByteArray();
        convertJavaToRedisBitmap(targetBitmap);
        return targetBitmap;
    }


    public static void convertJavaToRedisBitmap(byte[] bytes) {
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            byte b1 = bytes[i];
            if(b1 == 0) {
                continue;
            }
            byte transByte = 0;
            for (byte j = 0; j < 8; j++) {
                transByte |= (b1 & (1 << j)) >> j << (7 -j);
            }
            bytes[i] = transByte;
        }
    }
}
