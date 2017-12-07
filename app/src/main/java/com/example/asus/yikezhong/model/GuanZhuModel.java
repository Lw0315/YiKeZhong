package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.MyApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import okhttp3.ResponseBody;
import com.example.asus.yikezhong.service.ApiManage;
import com.example.asus.yikezhong.utils.RetrofitUtils;

/**
 * Created by asus on 2017/11/30.
 */

public class GuanZhuModel implements IGuanZhuModel {
    @Override
    public void guanzhu(String uid, String followId, final GuanZhuCallBack guanZhuCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("followId",followId);
        new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.GuanZhu, map, new BaseCallBack<BaseResponseEntity>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(ResponseBody responseBody, BaseResponseEntity baseResponseEntity) throws IOException {
                guanZhuCallBack.GuanZhuSuccess(baseResponseEntity);
            }
        });
    }
}
