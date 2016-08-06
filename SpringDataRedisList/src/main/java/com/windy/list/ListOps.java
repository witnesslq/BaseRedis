package com.windy.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 可以理解为基于一个队列或者栈的操作.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 15:09
 */
@Component("listOps")
public class ListOps {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 压栈.
     * @author zhangjunyong
     * @date 2016/8/6 15:11
     */
    public Long push(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出栈.
     * @author zhangjunyong
     * @date 2016/8/6 15:12
     */
    public String pop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 入队.
     * @author zhangjunyong
     * @date 2016/8/6 15:13
     */
    public Long in(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出队.
     * @author zhangjunyong
     * @date 2016/8/6 15:13
     */
    public String out(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 栈、队列长.
     * @author zhangjunyong
     * @date 2016/8/6 15:14
     */
    public Long length(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 范围搜索.
     * @author zhangjunyong
     * @date 2016/8/6 15:16
     */
    public List<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除.
     * @author zhangjunyong
     * @date 2016/8/6 15:16
     */
    public void remove(String key, long i, String value) {
        stringRedisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 检索.
     * @author zhangjunyong
     * @date 2016/8/6 15:17
     */
    public String index(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    /**
     * 置值.
     * @author zhangjunyong
     * @date 2016/8/6 15:17
     */
    public void set(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 裁剪.
     * @author zhangjunyong
     * @date 2016/8/6 15:19
     */
    public void trim(String key, long start, int end) {
        stringRedisTemplate.opsForList().trim(key, start, end);
    }


}
