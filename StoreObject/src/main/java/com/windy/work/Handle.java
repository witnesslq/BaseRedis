package com.windy.work;

import com.windy.model.Person;
import com.windy.util.SerializeUtil;
import redis.clients.jedis.Jedis;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 18:42
 */
public class Handle {
    public void setObject(Person person, Jedis jedis) {
        jedis.set(person.toString().getBytes(), SerializeUtil.serialize(person));
    }
}
