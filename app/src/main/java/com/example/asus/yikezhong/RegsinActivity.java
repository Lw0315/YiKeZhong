package com.example.asus.yikezhong;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.yikezhong.base.BaseActivity;

import com.example.asus.yikezhong.presenter.RegPresenter;
import com.example.asus.yikezhong.utils.StatusBarUtils;
import com.example.asus.yikezhong.view.RegView;

public class RegsinActivity extends BaseActivity<RegPresenter> implements RegView {
     private ImageView regist_back;
     private TextView hava_regist;
     private EditText et_name,et_pwd;
     private Button reg_button;
    @Override
    public int layoutId() {
        return R.layout.activity_regsin;
    }

    @Override
    public RegPresenter getPresenter() {
        return new RegPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_regsin);
//    }

    @Override
    public void setMyClick(View view) {
switch (view.getId()){
    case R.id.regist_back:
        finish();

        break;
    case R.id.hava_regist:
        startActivity(LoginActivity.class);

        break;
    case R.id.reg_button:
        String name=et_name.getText().toString().trim();
        String pwd=et_pwd.getText().toString().trim();
        presenter.reg(name,pwd);
        break;

}
    }

    @Override
    public void init() {
        StatusBarUtils.setStatus(this,true);
        regist_back=findViewById(R.id.regist_back);
        hava_regist=findViewById(R.id.hava_regist);
        regist_back.setOnClickListener(this);
        hava_regist.setOnClickListener(this);
        et_name=findViewById(R.id.et_name);
        et_pwd=findViewById(R.id.et_pwd);
        reg_button=findViewById(R.id.reg_button);
        reg_button.setOnClickListener(this);


    }

    @Override
    public void Success(String msg) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void RegSuccess(String msg) {
        System.out.println("======="+msg);
        startActivity(ZhuceActivity.class);
        finish();
    }
}
