package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.model.RegCallBack;
import com.example.asus.yikezhong.model.RegPwdModel;
import com.example.asus.yikezhong.view.RegPwdView;

/**
 * Created by asus on 2017/11/28.
 */

public class RegPwdPresenter extends BasePresenter<RegPwdView> {
    private RegPwdModel regPwdModel;
    private RegPwdView regPwdView;
    public RegPwdPresenter(RegPwdView mView) {
        super(mView);
        regPwdModel=new RegPwdModel();
        this.regPwdView=mView;
    }
    public void xiugai(String uid,String old,String news){
        regPwdModel.RegPwd(uid, old, news, new RegCallBack() {
            @Override
            public void success(String msg) {
                regPwdView.xiugai(msg);
            }

            @Override
            public void failure(String msg) {

            }
        });
    }
}
