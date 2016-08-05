package com.windy.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 21:46
 */
public class SpringRedisMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShardedJedisPool pool = null;
        ShardedJedis jedis = null;
        for (int i = 0; i < 100; i++) {
            try {
                pool = (ShardedJedisPool) applicationContext.getBean("shardedJedisPool");
                // 从池中获取一个Jedis对象
                jedis = pool.getResource();
                String keys = "name";
                String value = "snowolf";
                // 删数据
                jedis.del(keys);
                // 存数据
                jedis.set(keys, value);
                // 取数据
                String v = jedis.get(keys);
                System.out.println(v);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放对象池
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        if (pool != null) {
            pool.close();
        }
    }
}
