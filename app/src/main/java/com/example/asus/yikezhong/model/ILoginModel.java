package com.example.asus.yikezhong.model;

/**
 * Created by asus on 2017/11/28.
 */

public interface ILoginModel {
    void login(String mobile,String pwd,MyCallBack myCallBack);
    void reg(String mobile,String pwd,MyCallBack myCallBack);
}
