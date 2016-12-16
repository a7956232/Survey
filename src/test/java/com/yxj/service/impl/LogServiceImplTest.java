package com.yxj.service.impl;

import com.yxj.service.LogService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 95 on 2016/12/15.
 */
public class LogServiceImplTest {

    private static LogService ls;

    @BeforeClass
    public static void iniLogService(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ls = (LogService) ac.getBean("logService");
    }
    @Test
    public void testFindNearestLogs() throws Exception {
        ls.findNearestLogs(3);
    }
}