package com.example.asus.yikezhong.model;

import java.util.List;

import com.example.asus.yikezhong.bean.QuarterBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.utils.NetRequestUtils;

/**
 * Created by asus on 2017/11/28.
 */

public class QuarterModel implements IQuarterModel {
    private Quarter quart;

    public interface Quarter{
        void DuanZi(QuarterBean bean);
    }

    public void setQuarter(Quarter quart) {
        this.quart = quart;
    }

    @Override
    public void duanzi(String page) {
        new NetRequestUtils.Builder()
                .addCallAdapter(RxJava2CallAdapterFactory.create())
                .addConverter(GsonConverterFactory.create())
                .build().getApiService()
                .quarter(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<QuarterBean>() {
                    @Override
                    public void accept(QuarterBean user) throws Exception {

                       quart.DuanZi(user);
                        System.out.println("----------------------"+user.getMsg());

                    }
                });
    }

    @Override
    public void publish(String uid, String content,List<String> path) {

    }
}
