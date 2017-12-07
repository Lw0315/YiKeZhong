package com.example.asus.yikezhong.model;

/**
 * Created by asus on 2017/12/7.
 */

public interface IShouCangModel {
    void shoucang(String uid,String wid,ShouCangCallBack shouCangCallBack);
    void  quxiao(String uid,String wid,ShouCangCallBack shouCangCallBack);
}
