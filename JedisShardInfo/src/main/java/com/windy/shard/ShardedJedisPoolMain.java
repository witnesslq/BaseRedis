package com.windy.shard;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 21:01
 */
public class ShardedJedisPoolMain {
    public static void main(String[] args) {
        ShardedJedisPool shardedJedisPool = null;
        ShardedJedis jedis = null;

        //测试两个redis服务器中是否都有数据:结果显示一个数据只会保存在某一台redis服务器上面，不会保存在多台
        for (int i = 0; i < 10; i++) {
            try {
                shardedJedisPool = ShardedJedisPoolUtil.getShardedJedisPool();
                //从池中获取一个对象
                jedis = shardedJedisPool.getResource();
                String keys = "name" + i;
                String value = "xiaoming" + i;
                jedis.del(keys);
                jedis.set(keys, value);
                System.out.println(jedis.get(keys));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        if (shardedJedisPool != null) {
            shardedJedisPool.close();
        }
    }
}
