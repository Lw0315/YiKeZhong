package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.GetUserBean;
import com.example.asus.yikezhong.model.GetUserModel;
import com.example.asus.yikezhong.view.GetUserView;

/**
 * Created by asus on 2017/11/29.
 */

public class GetUserPresenter extends BasePresenter<GetUserView> implements GetUserModel.GetUser{
    private GetUserModel getUserModel;
    private GetUserView getUserView;
    public GetUserPresenter(GetUserView mView) {
        super(mView);
        getUserModel=new GetUserModel();
        this.getUserView=mView;
        getUserModel.setUser(this);
    }
    public void getUser(String uid){
        getUserModel.getUser(uid);
    }

    @Override
    public void GetUser(GetUserBean guser) {
        getUserView.GetUser(guser);
    }
}
