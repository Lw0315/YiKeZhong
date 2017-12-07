package com.example.asus.yikezhong.utils;

/**
 * Created by asus on 2017/11/14.
/
/**
 * 沉浸式工具类
 */
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class StatusBarUtils {
    /**
     * 透明状态栏
     * @param appCompatActivity
     *
     */
    private static boolean isStatus;
    public static void TransparentStatusbar(AppCompatActivity appCompatActivity,boolean isStatus) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = appCompatActivity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            appCompatActivity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            appCompatActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >=19 && Build.VERSION.SDK_INT < 21){
            if (isStatus){

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){

                    appCompatActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                }
            }
        }
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar!=null)
            actionBar.hide();

    }
    /**
     * 全屏模式(播放电影或者游戏模式)
     * 重写AppCompatActivity的onWindowFocusChanged方法
     * @param hasFocus
     * @param appCompatActivity
     */
    public static void FullScreenMode(Boolean hasFocus, AppCompatActivity appCompatActivity) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = appCompatActivity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public static void FullScreenMod(Boolean hasFocus, AppCompatActivity appCompatActivity) {

    }

    public static void setStatus(AppCompatActivity appCompatActivity,boolean status) {
        isStatus = status;
        if (isStatus){

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){

                appCompatActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            }
        }
    }
}
