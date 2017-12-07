package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.MyApp;
import com.example.asus.yikezhong.bean.GetUserShiPin;
import com.example.asus.yikezhong.service.ApiManage;
import com.example.asus.yikezhong.utils.RetrofitUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by asus on 2017/12/6.
 */

public class GetUserSpModel implements IGetUserSpModel {
    @Override
    public void getusersp(String uid, String page, final GetUserSpCallBack callBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("page",page);
        new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.GetUserSp, map, new BaseCallBack<GetUserShiPin>() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(ResponseBody responseBody, GetUserShiPin baseResponseEntity) throws IOException {
                callBack.GetUserSp(baseResponseEntity);
            }
        });
    }
    }

