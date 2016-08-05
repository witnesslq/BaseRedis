package com.windy.pool.maxtotal;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 19:56
 */
public class JedisPoolUtil {

    public static JedisPool getJedisPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException("redis.properties not found!");
        }
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
        config.setMaxTotal(5);
        //最大空闲连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
        config.setMaxIdle(Integer.valueOf(bundle
                .getString("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(bundle
                .getString("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle
                .getString("redis.pool.testOnReturn")));
        JedisPool jedisPool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString
                ("redis.port")));
        return jedisPool;
    }
}
