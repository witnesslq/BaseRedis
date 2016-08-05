package com.windy.shard;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 19:56
 */
public class ShardedJedisPoolUtil {

    public static ShardedJedisPool getShardedJedisPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException("redis.properties not found!");
        }
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
        config.setMaxTotal(Integer.valueOf(bundle
                .getString("redis.pool.maxTotal")));
        //最大空闲连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
        config.setMaxIdle(Integer.valueOf(bundle
                .getString("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWaitMillis")));
        config.setTestOnBorrow(Boolean.valueOf(bundle
                .getString("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle
                .getString("redis.pool.testOnReturn")));

        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(bundle.getString("redis1.ip"), Integer.valueOf(bundle
                .getString("redis1.port")));
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(bundle.getString("redis2.ip"), Integer.valueOf(bundle
                .getString("redis2.port")));
        List<JedisShardInfo> lists = new LinkedList<JedisShardInfo>();
        lists.add(jedisShardInfo1);
        lists.add(jedisShardInfo2);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, lists);
        return shardedJedisPool;
    }
}
