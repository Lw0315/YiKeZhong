package com.example.asus.yikezhong.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.asus.yikezhong.model.BaseCallBack;
import com.example.asus.yikezhong.service.Api;
import com.example.asus.yikezhong.service.ApiService;
import com.example.asus.yikezhong.service.BaseUrl;
import com.google.gson.Gson;

/**
 * Created by asus on 2017/12/4.
 */

public class NetUrils {
    private Api api;
    private static OkHttpClient okHttpClient;
    private static String Url= BaseUrl.Url;
    private static Context mcontext;
    private static Retrofit retrofit;
    private static Gson gson;

    private static class SingletonHolder{
        private static final NetUrils INSTANCE=new NetUrils(mcontext);
    }
    public static NetUrils getInstance(Context context){
        if(context!=null){
            mcontext=context;
            gson=new Gson();
        }
        return NetUrils.SingletonHolder.INSTANCE;
    }

    private NetUrils(Context context,String url){
        this(context,url,null);
    }
    public NetUrils(Context context){
        this(context,Url,null);
    }
    private NetUrils(Context context,String url,Map<String,String> heads){
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())//添加拦截器
                .connectTimeout(60, TimeUnit.SECONDS)//请求超时
                .readTimeout(60,TimeUnit.SECONDS)
                .build();
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://120.27.23.105/")
                .build();
    }
    public <T> T create(final Class<T> service){
        if(service==null){
            throw new RuntimeException("service为空");
        }
        return retrofit.create(service);
    }
    public NetUrils createBaseApi(){
        api=create(Api.class);
        return  this;
    }
    public void requestData(String url, Map<String,String> body, final BaseCallBack baseCallBack){
        Observable<ResponseBody> responseBodyObservable=api.executePost(url,body);
        responseBodyObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            final Object o=gson.fromJson(string,baseCallBack.type);
                            baseCallBack.onNext(responseBody,o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(Throwable e) {

                        Log.e("错误:", e + "");
                        baseCallBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        baseCallBack.onComplete();
                    }
                });
    }

}
