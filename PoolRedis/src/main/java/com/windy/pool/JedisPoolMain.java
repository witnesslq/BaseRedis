package com.windy.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 19:28
 */
public class JedisPoolMain {
    private static JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    public static void main(String[] args) {
        Jedis jedis = jedisPool.getResource();
        try {
            //从池中选取一个Jedis对象
            String keys = "name";
            String value = "xiaoming";
            jedis.del(keys);
            jedis.set(keys, value);
            String v = jedis.get(keys);
            System.out.println(v);
        } catch (Exception e) {

        } finally {
            //释放对象池
            if (jedis != null) {
                jedis.close();
            }
        }
        if (jedisPool != null)
            jedisPool.close();
    }
}
