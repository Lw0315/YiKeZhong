package com.example.asus.yikezhong.service;

import java.util.List;
import java.util.Map;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.bean.GetUserBean;
import com.example.asus.yikezhong.bean.QuarterBean;
import com.example.asus.yikezhong.bean.UserLogin;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by asus on 2017/11/14.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST()
    Observable<BaseResponseEntity> request(@Url String path, @FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponseEntity<UserLogin>> get(@Field("mobile")String mob, @Field("password") String pwd);
    @POST("user/reg")

    Observable<BaseResponseEntity> reg(@Field("mobile")String mob, @Field("password")String pwd);

    @FormUrlEncoded
    @POST("quarter/getJokes")
    Observable<QuarterBean> quarter(@Field("page")String page);

    @Multipart
    @POST("quarter/publishJoke")
    Observable<BaseResponseEntity> getpublishJoke(@Part() List<MultipartBody.Part> file);

//    @POST("quarter/publishJoke")
//    @FormUrlEncoded
//    Observable<BaseResponseEntity> publishJoke(@Field("uid") String uid, @Field("content")String content,@Field("jokeFiles") String jokeFiles);
    @POST("quarter/resetPass")
    @FormUrlEncoded
    Observable<BaseResponseEntity> resetPass(@Field("uid") String uid, @Field("oldPassword")String oldPassword,@Field("newPassword")String newPassword);

    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<GetUserBean> getuser(@Field("uid")String uid);


    @Multipart
    @POST("file/upload")
    Call<ResponseBody> getdata(@Query("uid") String uid, @Part MultipartBody.Part file);



    @Multipart
    @POST("quarter/publishVideo")
    Observable<BaseResponseEntity> getpublishVideo(@Part() List<MultipartBody.Part> file);



}
