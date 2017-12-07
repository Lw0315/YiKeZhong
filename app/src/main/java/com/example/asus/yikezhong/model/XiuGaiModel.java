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
 * Created by asus on 2017/11/29.
 */

public class XiuGaiModel implements IXIuGaiModel {
    @Override
    public void xiugai(String uid, String nickname, final XiuGaiCallBack xiuGaiCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("nickname",nickname);
       new RetrofitUtils(MyApp.context).getInstance(MyApp.context).createBaseApi().requestData(ApiManage.Xiugai, map, new BaseCallBack<BaseResponseEntity>() {
           @Override
           public void onError(Throwable throwable) {

           }

           @Override
           public void onComplete() {

           }

           @Override
           public void onNext(ResponseBody responseBody, BaseResponseEntity baseResponseEntity) throws IOException {
                    xiuGaiCallBack.Success(baseResponseEntity);
           }
       });
    }


}
