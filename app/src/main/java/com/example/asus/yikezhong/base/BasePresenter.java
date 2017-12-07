package com.example.asus.yikezhong.base;

/**
 * Created by asus on 2017/11/14.
 */

public class BasePresenter<V> {
    private V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
    public void deatach(){
        this.mView=null;
    }
}
