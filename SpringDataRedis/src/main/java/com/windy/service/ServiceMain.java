package com.windy.service;

import com.windy.dao.UserDao;
import com.windy.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 13:48
 */
public class ServiceMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");

        // -------------- Create ---------------
        String uid = "u123456";
        String address1 = "上海";
        User user = new User();
        user.setAddress(address1);
        user.setUid(uid);
        userDao.save(user);

        // ---------------Read ---------------
        user = userDao.read(uid);


        // --------------Update ------------
        String address2 = "北京";
        user.setAddress(address2);
        userDao.save(user);

        user = userDao.read(uid);


        // --------------Delete------------
        userDao.delete(uid);
        user = userDao.read(uid);
    }
}
