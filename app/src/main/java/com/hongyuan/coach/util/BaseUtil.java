package com.hongyuan.coach.util;

import android.text.TextUtils;

public class BaseUtil {

    /*
    * 判断是否有值
    * */
    public static boolean isValue(Object value){
        if(value == null || TextUtils.isEmpty(value.toString()) || "null".equals(value.toString())){
            return false;
        }
        return true;
    }

    /*
    * 去零处理
    * */
    public static String getNoZoon(Object number){
        String s = String.valueOf(number);
        if(s != null){
            if(s.indexOf(".") > 0){
                s = s.replaceAll("0+?$", "");//去掉多余的0
                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }
        return s;
    }
}
