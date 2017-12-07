package com.example.asus.yikezhong.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by asus on 2017/11/14.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener{
    public abstract int layoutId();
    public P presenter;

    public abstract P getPresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // StatusBarUtils.TransparentStatusbar(this,true);
        getSupportActionBar().hide();
        setContentView(layoutId());
        presenter=getPresenter();
       //setStatus(true);
        init();

    }

    public abstract void setMyClick(View view);
    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.deatach();
        }
    }

    @Override
    public void onClick(View view) {
        setMyClick(view);
    }
    /**
     * 无参跳转
     * @param clz
     */
    public void startActivity(Class<?> clz){
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
    /**
     * 有参跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent = new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
