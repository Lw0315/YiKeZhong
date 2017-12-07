package com.example.asus.yikezhong.model;

import com.example.asus.yikezhong.MyApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.asus.yikezhong.bean.ShiPinBean;

import okhttp3.ResponseBody;
import com.example.asus.yikezhong.service.ApiManage;
import com.example.asus.yikezhong.utils.RetrofitUtils;

/**
 * Created by asus on 2017/12/1.
 */

public class GetSpModel implements IGetSPModel {
    @Override
    public void getSp(String uid,String type, String page, final GetSpCallBack getSpCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("type",type);
        map.put("page",page);
        new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.GetSp, map, new BaseCallBack<ShiPinBean>() {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(ResponseBody responseBody, ShiPinBean baseResponseEntity) throws IOException {
                getSpCallBack.getSpSuccess(baseResponseEntity);
            }
        });
    }
}
