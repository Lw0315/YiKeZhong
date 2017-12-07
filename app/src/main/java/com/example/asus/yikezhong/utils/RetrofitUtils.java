package com.example.asus.yikezhong.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.example.asus.yikezhong.model.BaseCallBack;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.service.Api;
import com.example.asus.yikezhong.service.BaseUrl;

/**
 * Created by asus on 2017/11/29.
 */

public class RetrofitUtils {
   private Api api;
   private static OkHttpClient okHttpClient;
   private static String Url= BaseUrl.Url;
   private static Context mcontext;
   private static Retrofit retrofit;
   private static Gson gson;

   private static class SingletonHolder{
      private static final RetrofitUtils INSTANCE=new RetrofitUtils(mcontext);
   }
   public static RetrofitUtils getInstance(Context context){
      if(context!=null){
         mcontext=context;
         gson=new Gson();
      }
      return SingletonHolder.INSTANCE;
   }

   private RetrofitUtils(Context context,String url){
      this(context,url,null);
   }
   public RetrofitUtils(Context context){
      this(context,Url,null);
   }
   private RetrofitUtils(Context context,String url,Map<String,String> heads){
      okHttpClient=new OkHttpClient.Builder()
              .addInterceptor(new LogInterceptor())//添加拦截器
              .connectTimeout(60, TimeUnit.SECONDS)//请求超时
              .readTimeout(60,TimeUnit.SECONDS)
              .build();
      retrofit=new Retrofit.Builder()
              .client(okHttpClient)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .baseUrl(Url)
              .build();
   }
   public <T> T create(final Class<T> service){
      if(service==null){
         throw new RuntimeException("service为空");
      }
      return retrofit.create(service);
   }
   public RetrofitUtils createBaseApi(){
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
