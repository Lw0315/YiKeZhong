package com.example.asus.yikezhong;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.yikezhong.base.BaseActivity;

import com.example.asus.yikezhong.presenter.RegPwdPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.RegPwdView;

import static com.example.asus.yikezhong.R.id.for_already_regist;

public class XiugaiActivity extends BaseActivity<RegPwdPresenter> implements RegPwdView {
    private ImageView back;
    private TextView fortwo_already_regist;
    private Button success;
    private EditText et_name,et_pwd;
    private SharedPreferences sp;
    private int uid;
    @Override
    public int layoutId() {
        return R.layout.activity_xiugai;
    }

    @Override
    public RegPwdPresenter getPresenter() {
        return new RegPwdPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xiugai);
//    }

    @Override
    public void setMyClick(View view) {
        String name=et_name.getText().toString().trim();
        String pwd=et_pwd.getText().toString().trim();
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.fortwo_already_regist:
                startActivity(LoginActivity.class);
                break;
            case R.id.success:
                presenter.xiugai(uid+"",name,pwd);

                break;
        }

    }

    @Override
    public void init() {
        sp= SharedPreferencesUtil.getPreferences();
        uid=sp.getInt("uid",0);
        back=findViewById(R.id.back);
        fortwo_already_regist=findViewById(R.id.fortwo_already_regist);
        success=findViewById(R.id.success);
        back.setOnClickListener(this);
        fortwo_already_regist.setOnClickListener(this);
        success.setOnClickListener(this);
        et_name=findViewById(R.id.et_name);
        et_pwd=findViewById(R.id.et_pwd);

    }

    @Override
    public void Success(String msg) {


    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void xiugai(String msg) {
        System.out.println("========="+msg);
        startActivity(ZhuceActivity.class);

    }
}
