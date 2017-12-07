package com.example.asus.yikezhong;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by asus on 2017/11/14.
 */

public class MyApp extends Application {
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    public static  Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        UMShareAPI.get(this);
        context=this;
        MobSDK.init(context, "227a8c0432c76", "3b4edd1119d707ec93ed21bc1e20ae0b");
        Fresco.initialize(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
