package com.example.asus.yikezhong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;

public class ChuangzuoActivity extends BaseActivity {
    private TextView back;
    private ImageView duanzi;
    private ImageView shipin;
    @Override
    public int layoutId() {
        return R.layout.activity_chuangzuo;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chuangzuo);
//    }

    @Override
    public void setMyClick(View view) {
       switch (view.getId()){
           case R.id.back:
               startActivity(ZhuceActivity.class);
              finish();
               break;
           case R.id.duanzi:
              startActivity(BianXiewActivity.class);
              finish();
               break;
           case R.id.shipin:
               startActivity(BianXieShiPin.class);
               break;
       }
    }

    @Override
    public void init() {

      back=findViewById(R.id.back);
      back.setOnClickListener(this);
      duanzi=findViewById(R.id.duanzi);
      duanzi.setOnClickListener(this);
      shipin=findViewById(R.id.shipin);
      shipin.setOnClickListener(this);
    }
}
