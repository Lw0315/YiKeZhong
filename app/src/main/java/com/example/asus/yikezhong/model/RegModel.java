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

public class RegModel implements ILoginModel {
    @Override
    public void reg(String mobile, String pwd, final MyCallBack myCallBack) {
        new NetRequestUtils.Builder().addCallAdapter(RxJava2CallAdapterFactory.create())
                .addConverter(GsonConverterFactory.create())
                .build().getApiService()
                .reg(mobile,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseEntity>() {
                    @Override
                    public void accept(BaseResponseEntity user) throws Exception {
                        myCallBack.Regin(user.msg);

                    }
                });
    }
    @Override
    public void login(String mobile, String pwd, MyCallBack myCallBack) {

    }

}
