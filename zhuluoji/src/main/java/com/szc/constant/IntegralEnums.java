package com.szc.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 积分玩法 枚举
 */
@Getter
@AllArgsConstructor
public enum IntegralEnums {
    SIGN_IN(0, "1"),
    SIGN_IN7(1, "7"),
    SIGN_IN15(2, "15"),
    SIGN_IN25(3, "25");

    private final int key;
    private final String value;


    public static int getKeys(String value) {
        IntegralEnums[] values = IntegralEnums.values();
        for (IntegralEnums enums : values) {
            if (enums.getValue().equals(value)) {
                return enums.getKey();
            }
        }
        return -1;
    }

}
