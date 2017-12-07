package com.example.asus.yikezhong.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by asus on 2017/11/29.
 */

public interface Api {
    @POST()
    @FormUrlEncoded
    @Headers("Accept:*")
    Observable<ResponseBody> executePost(@Url String url, @FieldMap Map<String, String> maps);
}
