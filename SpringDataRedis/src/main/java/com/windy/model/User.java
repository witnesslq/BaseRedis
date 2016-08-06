package com.windy.model;

import java.io.Serializable;

/**
 * 描述.
 * @author zhangjunyong
 * @version V1.0  2016/8/6 13:41
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8174693164822753860L;
    private String uid;
    private String address;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
