package com.itdr.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {



    // 加
    public static BigDecimal add(double d,double d1){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        return bigDecimal.add(bigDecimal1);
    }




    // 乘
    public static BigDecimal mul(double d, double d1){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d1));
        return bigDecimal.multiply(bigDecimal1);
    }
}
