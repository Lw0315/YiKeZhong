package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.UserLogin;
import com.example.asus.yikezhong.model.LoginModel;
import com.example.asus.yikezhong.model.MyCallBack;
import com.example.asus.yikezhong.view.LoginView;

/**
 * Created by asus on 2017/11/27.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel loginModel;
    private LoginView loginView;
    public LoginPresenter(LoginView mView) {
        super(mView);
        loginModel=new LoginModel();
        this.loginView=mView;
    }
    public void login(String mobile,String pwd){
        loginModel.login(mobile, pwd, new MyCallBack() {
            @Override
            public void success(UserLogin data) {
                loginView.Success(data.msg);
                loginView.LoginToken(data.token);
                loginView.LoginId(data.uid);
            }

            @Override
            public void failure(UserLogin msg) {

            }

            @Override
            public void Regin(String msg) {

            }
        });
    }
}
