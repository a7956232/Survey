package com.yxj.util;

import com.opensymphony.xwork2.ActionContext;

/**
 * Created by 95 on 2016/11/23.
 */
public class ActionUtil {
    public static final String REDIRECT = "redirect";

    public static void setUrl(String url){
        ActionContext.getContext().put("url",url);
    }
}
