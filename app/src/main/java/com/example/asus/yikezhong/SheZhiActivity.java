package com.example.asus.yikezhong;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.zidingyi.View_Title;

public class SheZhiActivity extends BaseActivity {
    private View_Title view_title;
    private Button btn_tuichu;
    private SharedPreferences sp;
    private int uid;
    @Override
    public int layoutId() {
        return R.layout.activity_she_zhi;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_she_zhi);
//    }

    @Override
    public void setMyClick(View view) {
     switch (view.getId()){
    case R.id.btn_tuichu:
        SharedPreferencesUtil.clearPreferences("uid");
        startActivity(LoginActivity.class);
        break;
     }
    }

    @Override
    public void init() {


    view_title=findViewById(R.id.view_title);
    view_title.setText("设置");
    view_title.setOnClickListenler(new View_Title.OnClickListener() {
        @Override
        public void ImageClicked(View v) {
            finish();
        }
    });
    btn_tuichu=findViewById(R.id.btn_tuichu);
    btn_tuichu.setOnClickListener(this);
    }
}
