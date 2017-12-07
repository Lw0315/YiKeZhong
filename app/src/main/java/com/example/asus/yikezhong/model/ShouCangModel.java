package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.MyApp;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.service.ApiManage;
import com.example.asus.yikezhong.utils.RetrofitUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by asus on 2017/12/7.
 */

public class ShouCangModel  implements IShouCangModel {
    @Override
    public void shoucang(String uid, String wid, final ShouCangCallBack shouCangCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("wid",wid);
        new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.ShouCang,map, new BaseCallBack<BaseResponseEntity>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(ResponseBody responseBody, BaseResponseEntity baseResponseEntity) throws IOException {
              shouCangCallBack.ShouCangSuccess(baseResponseEntity);
            }
        });
    }

    @Override
    public void quxiao(String uid, String wid, final ShouCangCallBack shouCangCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("wid",wid);
        new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.QuXiaoShouCang,map, new BaseCallBack<BaseResponseEntity>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(ResponseBody responseBody, BaseResponseEntity baseResponseEntity) throws IOException {
                shouCangCallBack.ShouCangSuccess(baseResponseEntity);
            }
        });
    }


}
