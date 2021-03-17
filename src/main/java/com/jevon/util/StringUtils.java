package com.jevon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static String getCurrentTimeStr(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static boolean isNullOrEmpty(String toTest) {
        return toTest == null || toTest.isEmpty();
    }
}
