package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.bean.UserLogin;

/**
 * Created by asus on 2017/11/28.
 */

public interface MyCallBack {
    void success(UserLogin msg);
    void failure(UserLogin msg);
    void Regin(String msg);
}
