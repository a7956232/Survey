package com.yxj.advice;

import com.opensymphony.xwork2.ActionContext;
import com.yxj.entity.Log;
import com.yxj.entity.User;
import com.yxj.service.LogService;
import com.yxj.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by 95 on 2016/12/15.
 */
public class Logger {

    @Resource
    private LogService logService;

    //记录
    public Object record(ProceedingJoinPoint pjp){
        Log log = new Log();
        try {
            ActionContext ac = ActionContext.getContext();
            //设置操作人
            if(ac != null){
                Map<String,Object> session = ac.getSession();
                if(session != null){
                    User user = (User) session.get("existUser");
                    if(user != null){
                        log.setOperator("" + user.getId() + ":" + user.getUsername());
                    }
                }
            }
            //设置操作名称
            String mname = pjp.getSignature().getName();
            log.setOperName(mname);
            //操作参数
            Object[] params = pjp.getArgs();
            log.setOperParams(StringUtil.arr2Str(params));
            //调用目标对象的方法
            Object ret = pjp.proceed();
            //设置操作结果
            log.setOperResult("success");
            //设置结果消息
            if(ret != null){
                log.setResultMsg(ret.toString());
            }
            return ret;
        } catch (Throwable throwable) {
            log.setOperResult("failure");
            log.setResultMsg(throwable.getMessage());
        }
        finally {
            logService.saveEntity(log);
        }
        return null;
    }
}
