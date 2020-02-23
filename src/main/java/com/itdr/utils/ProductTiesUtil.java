package com.itdr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProductTiesUtil {
    private static Properties p;
    static {
        InputStream in = ProductTiesUtil.class.getClassLoader().getResourceAsStream("tu.properties");
        p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        String property = p.getProperty(key);
        return property;
    }
}
