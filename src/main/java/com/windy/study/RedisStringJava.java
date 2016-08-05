package com.windy.study;

import redis.clients.jedis.Jedis;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/4 14:19
 */
public class RedisStringJava {
    public static void main(String[] args) {
        //Connecting to Redis Server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server successfully.");
        //set the data in model string
        jedis.set("name", "xiaoming");
        System.out.println("the string name in the model :" + jedis.get("name"));
        jedis.quit();
        jedis.close();
    }
}
