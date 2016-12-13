package com.yxj.util;

import java.io.*;
import java.security.MessageDigest;

/**
 * Created by 95 on 2016/11/21.
 */
public class DataUtil {
    //使用md5算法进行加密
    public static String md5(String src){
        try {
            StringBuffer buffer = new StringBuffer();
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
            byte[] bytes = src.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] targ = md.digest(bytes);
            for(byte b:targ){
                buffer.append(chars[(b >> 4) & 0x0F]);
                buffer.append(chars[b & 0x0F]);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //深度复制，复制的整个对象图，但transient修饰的属性对象不会被复制
    public static Serializable deeplyCopy(Serializable src){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(src);
            oos.close();
            baos.close();

            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Serializable copy = (Serializable) ois.readObject();
            ois.close();
            bais.close();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
