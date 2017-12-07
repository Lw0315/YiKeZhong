package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.UserLogin;
import com.example.asus.yikezhong.model.MyCallBack;
import com.example.asus.yikezhong.model.RegModel;
import com.example.asus.yikezhong.view.RegView;

/**
 * Created by asus on 2017/11/28.
 */

public class RegPresenter extends BasePresenter<RegView> {
    private RegView regView;
    private RegModel regModel;
    public RegPresenter(RegView mView) {
        super(mView);
        regModel=new RegModel();
        this.regView=mView;
    }
    public void reg(String mobile,String pwd){
        regModel.reg(mobile, pwd, new MyCallBack() {
            @Override
            public void success(UserLogin data) {

            }

            @Override
            public void failure(UserLogin msg) {

            }

            @Override
            public void Regin(String msg) {
              regView.RegSuccess(msg);
            }
        });
    }
}
