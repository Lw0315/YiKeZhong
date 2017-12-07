package com.example.asus.yikezhong;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.utils.StatusBarUtils;

public class MainActivity extends BaseActivity {
    private Handler handler=new Handler();
    private Runnable run;
    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        StatusBarUtils.setStatus(this,true);
        run=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,ZhuceActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(run,3000);

    }
    @Override
    public void setMyClick(View view) {

    }
    @Override
    public void init() {
        getSupportActionBar().hide();

    }
}
