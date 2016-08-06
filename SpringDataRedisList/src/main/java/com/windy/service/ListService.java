package com.windy.service;

import com.windy.list.ListOps;
import com.windy.util.ListOpsUtil;

import java.util.List;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 15:26
 */
public class ListService {

    //问题:感觉都没有释放连接，因为是网上的例子和作为入门，目前不做深入研究
    private ListOps listOps = ListOpsUtil.getListOps();
    private String key = "queue";

    public void in() throws Exception {
        System.out.println("------------IN---------------");
        for (int i = 0; i < 5; i++) {
            String uid = "u" + i;
            System.out.println(uid);
            listOps.in(key, uid);
        }
    }

    public void out() {
        // ------------OUT---------------
        System.out.println("------------OUT---------------");
        long length = listOps.length(key);
        for (long i = 0; i < length; i++) {
            String uid = listOps.out(key);
            System.out.println(uid);
        }
    }

    public void stack() {
        // ------------PUSH---------------
        String key = "stack";
        int len = 5;
        System.out.println("------------PUSH---------------");
        for (int i = 0; i < len; i++) {
            String uid = "u" + System.currentTimeMillis();
            System.out.println(uid);
            listOps.push(key, uid);
        }

        long length = listOps.length(key);

        // ------------POP---------------
        System.out.println("------------POP---------------");
        for (long i = 0; i < length; i++) {
            String uid = listOps.pop(key);
            System.out.println(uid);
        }
    }

    public void index() {
        // -------------INDEX-------------
        String value = listOps.index(key, 3);
    }

    public void range() {
        // -------------RANGE-------------
        System.out.println("------------RANGE---------------");
        List<String> list = listOps.range(key, 3, 5);
        boolean result1 = list.contains("u3");
        boolean result2 = list.contains("u1");
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("contain u3:" + result1);
        System.out.println("contain u1:" + result2);
    }

    public void trim() {
        // ------------TRIM---------------
        List<String> list = listOps.range(key, 3, 5);
        listOps.trim(key, 3, 5);
        boolean result3 = list.contains("u1");
    }

    public void set() {
        // ------------SET-----------------
        List<String> list = listOps.range(key, 3, 5);
        listOps.set(key, 4, "ux4");
        boolean result4 = list.contains("u4");
    }

    public void remove() {
        // ------------REMOVE-----------------
        listOps.remove(key, 4, "u4");
        String value = listOps.index(key, 4);
    }

}
