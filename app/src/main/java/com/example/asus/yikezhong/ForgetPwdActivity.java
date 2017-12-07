package com.example.asus.yikezhong;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import com.example.asus.yikezhong.utils.StatusBarUtils;

public class ForgetPwdActivity extends BaseActivity {
    private ImageView forget_back;
    private TextView for_already_regist;
    private EditText et_name,et_pwd;
    private TextView huoqu;
    private Button but_next;
    private int TIME=10;
    private final int SECOND=1000;
    private EventHandler eventHandler;
    private Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            TIME--;
            if(TIME==0){
                handler.removeCallbacks(this);
                TIME=10;
                huoqu.setEnabled(true);

            }else{
                huoqu.setEnabled(false);
                huoqu.setText(TIME+"s");
                handler.postDelayed(this,SECOND);
            }
        }
    };
    @Override
    public int layoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_pwd);
//    }

    @Override
    public void setMyClick(View view) {
        String nn=et_name.getText().toString().trim();
        String pp=et_pwd.getText().toString().trim();
        switch (view.getId()){
    case R.id.forget_back:
        finish();
        break;
    case R.id.for_already_regist:
        startActivity(LoginActivity.class);
        break;
    case R.id.huoqu:
        if(TextUtils.isEmpty(nn)){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        handler.postDelayed(runnable,SECOND);
        SMSSDK.getVerificationCode("86",nn);
        break;
    case R.id.but_next:
        if(TextUtils.isEmpty(nn)){
            Toast.makeText(ForgetPwdActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pp)){
            Toast.makeText(ForgetPwdActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        SMSSDK.submitVerificationCode("86",nn,pp);
        startActivity(XiugaiActivity.class);
        break;
       }
    }

    @Override
    public void init() {
        StatusBarUtils.setStatus(this,true);
       forget_back=findViewById(R.id.forget_back);
       for_already_regist=findViewById(R.id.for_already_regist);
       forget_back.setOnClickListener(this);
       for_already_regist.setOnClickListener(this);
       et_name=findViewById(R.id.et_name);
       et_pwd=findViewById(R.id.et_pwd);
       huoqu=findViewById(R.id.huoqu);
       huoqu.setOnClickListener(this);
       but_next=findViewById(R.id.but_next);
       but_next.setOnClickListener(this);

        registerSMS();
    }
    private void registerSMS() {
        //创建EventHandler对象
        eventHandler=new EventHandler(){
            public void afterEvent(int event,int result,Object data){
                if(data instanceof  Throwable){
                    Throwable throwable= (Throwable) data;
                    final String msg=throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForgetPwdActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //只有回服务器验证成功，才能允许用户登录
                    if(result== SMSSDK.RESULT_COMPLETE){
                        //回调完成,提交验证码成功
                        if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ForgetPwdActivity.this, "服务器验证成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else if(result==SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ForgetPwdActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        };
        //注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }
}
