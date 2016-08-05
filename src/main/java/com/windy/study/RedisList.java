package com.windy.study;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 9:33
 */
public class RedisList {
    public static void main(String[] args) {
        //连接本地的Redis服务
        Jedis jedis=new Jedis("localhost");
        System.out.println("Connection to server successfully");
        jedis.lpush("list","list1");
        jedis.lpush("list","list1");
        jedis.lpush("list","list2");
        jedis.lpush("list","list3");
        List<String> list=jedis.lrange("list",0,5);
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i));
        }
    }
}
