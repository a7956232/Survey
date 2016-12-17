package com.yxj.cache;

import com.yxj.util.StringUtil;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by 95 on 2016/12/17.
 */
public class SurveyKeyGenerator implements KeyGenerator{
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        String className = o.getClass().getSimpleName();
        String mname = method.getName();
        String params = StringUtil.arr2Str(objects);
        String key = className + "." + mname + "(" + params + ")";
        System.out.println(key);
        return key;
    }
}
