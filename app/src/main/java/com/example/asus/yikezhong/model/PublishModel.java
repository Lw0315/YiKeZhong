package com.example.asus.yikezhong.model;

import java.io.File;
import java.util.List;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.utils.NetRequestUtils;

/**
 * Created by asus on 2017/11/28.
 */

public class PublishModel implements IQuarterModel {
    private Publish publish;

    public interface Publish{
        void Publi(String bean);
    }

    public void setPublish(Publish publish) {
        this.publish = publish;
    }

    @Override
    public void duanzi(String page) {

    }

    @Override
    public void publish(final String uid, String content,List<String> path) {

        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        build.addFormDataPart("uid",uid+"");
        build.addFormDataPart("content",content);
        for (int i = 0; i <path.size() ; i++) {
            File file=new File(path.get(i));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            build.addFormDataPart("jokeFiles", file.getName(), requestFile);
        }
        List<MultipartBody.Part> parts = build.build().parts();
        new NetRequestUtils.Builder().addConverter(GsonConverterFactory.create())
            .addCallAdapter(RxJava2CallAdapterFactory.create())
            .build().getApiService().getpublishJoke(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseEntity>() {
                    @Override
                    public void accept(BaseResponseEntity user) throws Exception {
                        String msg = user.msg;
                        publish.Publi(msg);

                    }
                });
//        new NetRequestUtils.Builder().addCallAdapter(RxJava2CallAdapterFactory.create())
//                .addConverter(GsonConverterFactory.create())
//                .build().getApiService()
//                .publishJoke(uid,content,jokeFiles)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseResponseEntity>() {
//                    @Override
//                    public void accept(BaseResponseEntity user) throws Exception {
//                        String msg = user.msg;
//                        publish.Publi(msg);
//
//                    }
//                });
    }
}
