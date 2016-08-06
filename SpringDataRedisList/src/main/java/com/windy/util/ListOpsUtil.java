package com.windy.util;

import com.windy.list.ListOps;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 15:21
 */
public class ListOpsUtil {
    private static ApplicationContext applicationContext;
    private static ListOps listOps;
    private final String key = "queue";

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static ListOps getListOps() {
        listOps = (ListOps) applicationContext.getBean("listOps");
        return listOps;
    }
}
