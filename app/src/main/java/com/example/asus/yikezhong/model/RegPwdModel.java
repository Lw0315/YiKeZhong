package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.utils.NetRequestUtils;

/**
 * Created by asus on 2017/11/28.
 */

public class RegPwdModel implements IRegPwdModel {
    @Override
    public void RegPwd(String uid, String oldPassword, String newPassword, final RegCallBack regCallBack) {
        new NetRequestUtils.Builder().addCallAdapter(RxJava2CallAdapterFactory.create())
                .addConverter(GsonConverterFactory.create())
                .build().getApiService()
                .resetPass(uid,oldPassword,newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseEntity>() {
                    @Override
                    public void accept(BaseResponseEntity user) throws Exception {
                        String msg = user.msg;
                        regCallBack.success(msg);

                    }
                });
    }
}
