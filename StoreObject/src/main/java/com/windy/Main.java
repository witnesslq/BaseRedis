package com.windy;

import com.windy.model.Person;
import com.windy.util.SerializeUtil;
import com.windy.work.Handle;
import redis.clients.jedis.Jedis;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/5 18:46
 */
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person(1, "xiaoming1");
        Person person2 = new Person(2, "xiaoming2");
        Jedis jedis = new Jedis("localhost");
        Handle handle = new Handle();
        handle.setObject(person1, jedis);
        handle.setObject(person2, jedis);
        System.out.println(jedis.keys("*"));
        System.out.println(SerializeUtil.unserialize(jedis.get(person1.toString().getBytes())));
        System.out.println(SerializeUtil.unserialize(jedis.get(person2.toString().getBytes())));
        jedis.quit();
        jedis.close();
    }
}
