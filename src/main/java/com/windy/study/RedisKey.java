package com.windy.study;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 9:36
 */
public class RedisKey {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");

        jedis.set("test","test");
        // 获取数据并输出
       Set<String> set=jedis.keys("*");
        for(String str:set){
            System.out.println("List of stored keys:: "+str);
        }
    }
}
