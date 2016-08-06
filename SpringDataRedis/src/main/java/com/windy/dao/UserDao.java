package com.windy.dao;

import com.windy.model.User;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 13:46
 */
public interface UserDao {
    void save(User user);

    User read(String uid);

    void delete(String uid);

}
