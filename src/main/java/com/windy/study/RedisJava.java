package com.windy.study;

import redis.clients.jedis.Jedis;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/4 12:56
 */
public class RedisJava {
    public static void main(String[] args) {
        //Connecting to Redis Server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server successfully.");
        //check whether server is running or not
        System.out.println("Server is running:" + jedis.ping());
        jedis.quit();
        jedis.close();
    }
}
