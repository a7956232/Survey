package com.yxj.util;

/**
 * Created by 95 on 2016/11/24.
 */
//字符串工具类
public class StringUtil {
    //将字符串转换成数组，按照tag分隔
    public static String[] str2Arr(String str,String tag){
        if(ValidateUtil.isValid(str)){
            return str.split(tag);
        }
        return null;
    }

    //判断values数组中是否含有指定value字符串
    public static boolean contains(String[] values, String value) {
        if(ValidateUtil.isValid(values)){
            for(String s : values){
                if(s.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    //将数组转换成字符串，按照“，”分隔
    public static String arr2Str(String[] arr) {
        String temp = "";
        if(ValidateUtil.isValid(arr)){
            for(String s : arr){
                temp = temp + s + ",";
            }
            return temp.substring(0,temp.length() - 1);
        }
        return temp;
    }

    public static String arr2Str(Integer[] ids) {
        String temp = "";
        if(ValidateUtil.isValid(ids)){
            for(Integer s : ids){
                temp = temp + s + ",";
            }
            return temp.substring(0,temp.length() - 1);
        }
        return temp;
    }
}