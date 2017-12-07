package com.example.asus.yikezhong.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.service.ApiService;

/**
 * Created by asus on 2017/11/27.
 */

public class NetRequestUtils {
    public static NetRequestUtils netRequestUtils;
    public ApiService apiService;

    public NetRequestUtils(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }
    public static class Builder{
        private List<Converter.Factory> converters=new ArrayList<>();
        private List<CallAdapter.Factory> calladapters=new ArrayList<>();
        public Builder addConverter(Converter.Factory factory){
            converters.add(factory);
            return  this;
        }
        public Builder addCallAdapter(CallAdapter.Factory factory){
            calladapters.add(factory);
            return this;
        }
        public NetRequestUtils build(){
            OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
            okHttpClient.addInterceptor(new LogInterceptor())
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            ;
            Retrofit.Builder retrofit=new Retrofit.Builder().baseUrl("https://www.zhaoapi.cn").client(okHttpClient.build());
            if(converters.size()==0){
                converters.add(GsonConverterFactory.create());
            }else{
                for (Converter.Factory converter : converters) {

                    retrofit.addConverterFactory(converter);
                }
            }
            if(calladapters.size()==0){
                calladapters.add(RxJava2CallAdapterFactory.create());
            }else{
                for (CallAdapter.Factory calladapter : calladapters) {
                    retrofit.addCallAdapterFactory(calladapter);
                }
            }
            ApiService apiService=retrofit.build().create(ApiService.class);
            netRequestUtils=new NetRequestUtils(apiService);
            return netRequestUtils;
        }
    }

}
