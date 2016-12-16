package com.yxj.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by 95 on 2016/11/21.
 */
public abstract class BaseEntity implements Serializable{
    private static final long serialVersionUID = 4050287007163542822L;

    @Override
    public String toString() {
        try {
            StringBuffer buffer = new StringBuffer();
            Class clazz = this.getClass();
            String simpleName = clazz.getSimpleName();
            buffer.append(simpleName);
            buffer.append("{");
            //
            Field[] fs = clazz.getDeclaredFields();
            Class ftype;
            String fname;
            Object fvalue;
            for(Field f : fs){
                ftype = f.getType();
                fname = f.getName();
                f.setAccessible(true);
                fvalue = f.get(this);
                //是否是基本数据类型
                if((ftype.isPrimitive()
                        ||ftype == Integer.class
                        ||ftype == Long.class
                        ||ftype == Short.class
                        ||ftype == Boolean.class
                        ||ftype == Character.class
                        ||ftype == Double.class
                        ||ftype == Float.class
                        ||ftype == String.class)
                        && !Modifier.isStatic(f.getModifiers())){
                    buffer.append(fname);
                    buffer.append(":");
                    buffer.append(fvalue);
                    buffer.append(",");
                }
            }
            //
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append("}");
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
