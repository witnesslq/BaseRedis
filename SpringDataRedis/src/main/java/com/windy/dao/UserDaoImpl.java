package com.windy.dao;

import com.windy.dao.UserDao;
import com.windy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 13:54
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    //----这里是通过模板类，实现方法回调。在spring框架下，可以方便的控制事务，如果你研究过spring的dao源代码，对此一定熟悉。
    //    1传入参数，需要final标识，禁止方法内修改。
    //    2调用RedisConnection的set方法实现Redis的SET命令。
    //    3不管是Key，还是Value都需要进行Serialize。
    //    4序列化操作，最好使用RedisTemplate提供的Serializer来完成。

    /**
     * 保存操作.
     * @author zhangjunyong
     * @date 2016/8/6 14:04
     */
    @Override
    public void save(final User user) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getUid())
                        , redisTemplate.getStringSerializer().serialize(user.getAddress()));
                return null;
            }
        });
    }

    /**
     * 获取操作.
     * @author zhangjunyong
     * @date 2016/8/6 14:04
     */
    @Override
    public User read(final String uid) {
        return redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + uid);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String address = redisTemplate.getStringSerializer().deserialize(value);
                    User user = new User();
                    user.setAddress(address);
                    user.setUid(uid);
                    return user;
                }
                return null;
            }
        });
    }

    /**
     * 删除操作.
     * @author zhangjunyong
     * @date 2016/8/6 14:04
     */
    @Override
    public void delete(final String uid) {
        redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(redisTemplate.getStringSerializer().serialize("user.uid." + uid));
                return null;
            }
        });
    }
}
