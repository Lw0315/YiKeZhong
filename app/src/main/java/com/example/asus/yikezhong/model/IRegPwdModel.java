package com.example.asus.yikezhong.model;

/**
 * Created by asus on 2017/11/28.
 */

public interface IRegPwdModel {
    void RegPwd(String uid,String oldPassword,String newPassword,RegCallBack regCallBack);
}
