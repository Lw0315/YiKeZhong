package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.bean.GetUserBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.utils.NetRequestUtils;

/**
 * Created by asus on 2017/11/29.
 */

public class GetUserModel implements IGetUserModel {
    private GetUser guser;

    public interface GetUser{
        void GetUser(GetUserBean guser);
    }

    public void setUser(GetUser guser) {
        this.guser = guser;
    }

    @Override
    public void getUser(String uid) {
        new NetRequestUtils.Builder().addCallAdapter(RxJava2CallAdapterFactory.create())
                .addConverter(GsonConverterFactory.create())
                .build().getApiService()
                .getuser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetUserBean>() {
                    @Override
                    public void accept(GetUserBean user) throws Exception {
                        System.out.println("----------------------"+user.getMsg());
                        guser.GetUser(user);
                    }
                });
    }
}
