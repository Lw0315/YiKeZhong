package com.example.asus.yikezhong.model;

import java.io.File;
import java.util.List;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.asus.yikezhong.utils.NetRequestUtils;
import com.example.asus.yikezhong.utils.NetUrils;
import com.example.asus.yikezhong.utils.RetrofitUtils;

/**
 * Created by asus on 2017/12/1.
 */

public class ShiPinModel implements IShiPinModel {
    @Override
    public void ShiPin(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude, final ShiPinCallBack shiPinCallBack) {
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        build.addFormDataPart("uid",uid);
        build.addFormDataPart("videoDesc",videoDesc);
        build.addFormDataPart("latitude",latitude);
        build.addFormDataPart("longitude",longitude);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
        build.addFormDataPart("videoFile", videoFile.getName(), requestFile);
        RequestBody re = RequestBody.create(MediaType.parse("multipart/form-data"), coverFile);
        build.addFormDataPart("coverFile", coverFile.getName(),re);
        List<MultipartBody.Part> parts = build.build().parts();
        new NetRequestUtils.Builder().addConverter(GsonConverterFactory.create())
                .addCallAdapter(RxJava2CallAdapterFactory.create())
                .build().getApiService().getpublishVideo(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseEntity>() {
                    @Override
                    public void accept(BaseResponseEntity user) throws Exception {
                        String msg = user.msg;
                        shiPinCallBack.SuccessShiPin(user);

                    }
                });
    }
}
