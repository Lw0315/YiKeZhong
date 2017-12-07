package com.example.asus.yikezhong;

import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;

public class MyGuanZhuActivity extends BaseActivity {
     private XRecyclerView guanzhu_xrv;
    @Override
    public int layoutId() {
        return R.layout.activity_my_guan_zhu;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_guan_zhu);
//    }

    @Override
    public void setMyClick(View view) {

    }

    @Override
    public void init() {
      guanzhu_xrv=findViewById(R.id.guanzhu_xrv);
    }
}
