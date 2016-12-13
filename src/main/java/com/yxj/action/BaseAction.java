package com.yxj.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 95 on 2016/11/21.
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

    public T model;

    public BaseAction(){
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class) type.getActualTypeArguments()[0];
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
