package com.windy.pool.maxtotal;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 19:49
 */
public class JedisPoolMaxTotalMain {
    private static JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    public static void main(String[] args) {
        Jedis jedis = null;
        for (int i = 0; i < 10; i++) {
            try {
                jedis = jedisPool.getResource();
                System.out.println("the connection times:" + i);
                jedis.set("foo", "bar");
                System.out.println(jedis.get("foo"));
                jedis.zadd("zset", 0, "Zhang San");
                jedis.zadd("zset", 0, "Li Si");
                Set sose = jedis.zrange("zset", 0, -1);
                System.out.println(sose);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    //分析：这个代码跟注释了的比起来，只是把redis.close();这一行注释起来了，相当于程序员忘写这行代码。
                    //导致的结果就是只能得到5个结果。这是因为连接池的最大连接数设为5，然后测试时启用了10个连接，
                    //因为连接没有被释放，当最大连接数被用完后，后面的5个连接就连不上了，只能干等着。
                    jedis.close();
                }
            }
        }
        jedisPool.close();
    }

}
