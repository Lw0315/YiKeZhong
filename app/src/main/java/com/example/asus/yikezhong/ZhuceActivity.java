package com.example.asus.yikezhong;

import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.yikezhong.base.BaseActivity;

import com.example.asus.yikezhong.presenter.LoginPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.utils.StatusBarUtils;
import com.example.asus.yikezhong.view.LoginView;

public class ZhuceActivity extends BaseActivity<LoginPresenter> implements LoginView{
    private TextView tv_regist,forget_pwd;
    private ImageView iv_back;
    private EditText et_name,et_pwd;
    private Button login_button;
    private TextView login_in;
    private SharedPreferences sp;
    @Override
    public int layoutId() {
        return R.layout.activity_zhuce;
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_zhuce);
//    }

    @Override
    public void setMyClick(View view) {
       switch (view.getId()){
           case R.id.tv_regist:
                startActivity(RegsinActivity.class);

               break;
           case R.id.forget_pwd:
                startActivity(ForgetPwdActivity.class);

               break;
           case R.id.iv_back:
               finish();
               break;
           case R.id.login_in:
               startActivity(Main2Activity.class);
               break;
           case R.id.login_button:
               System.out.println("开始-----");
               String name=et_name.getText().toString().trim();
               String pwd=et_pwd.getText().toString().trim();
//               Map<String,String> map=new HashMap<>();
//               map.put("mobile",name);
//               map.put("password",pwd);
//
//               RetrofitFactory.getInstence().retrofitFactory.request("user/login", map, new Consumer<BaseResponseEntity>() {
//                   @Override
//                   public void accept(BaseResponseEntity baseResponseEntity) throws Exception {
//                       System.out.println("====="+baseResponseEntity.msg);
//                   }
//               });
               presenter.login(name,pwd);
               break;
       }
    }

    @Override
    public void init() {
         StatusBarUtils.setStatus(this,true);
          sp= SharedPreferencesUtil.getPreferences();
         tv_regist=findViewById(R.id.tv_regist);
         forget_pwd=findViewById(R.id.forget_pwd);
         iv_back=findViewById(R.id.iv_back);
         tv_regist.setOnClickListener(this);
         forget_pwd.setOnClickListener(this);
         iv_back.setOnClickListener(this);
         et_name=findViewById(R.id.et_name);
         et_pwd=findViewById(R.id.et_pwd);
         et_pwd.setInputType(InputType.TYPE_CLASS_NUMBER);
         login_button=findViewById(R.id.login_button);
         login_button.setOnClickListener(this);
        login_in=findViewById(R.id.login_in);
        login_in.setOnClickListener(this);

    }

    @Override
    public void Success(String msg) {
        startActivity(Main2Activity.class);
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void LoginToken(String msg) {
        System.out.println("====="+msg);
        sp.edit().putString("token",msg).commit();


    }

    @Override
    public void LoginId(int uid) {
        System.out.println("====="+uid);
        sp.edit().putInt("uid",uid).commit();
    }
}
