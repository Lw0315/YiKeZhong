package com.example.asus.yikezhong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.utils.StatusBarUtils;

public class LoginActivity extends BaseActivity {
    private TextView gengduo;
    private ImageView img,back;
    private Button qq;
    private SharedPreferences sp;
    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }

    @Override
    public void setMyClick(View view) {
       switch (view.getId()){
           case R.id.gengduo:
               startActivity(ZhuceActivity.class);

           break;
           case R.id.img:
               finish();
               break;
           case R.id.qq:
               UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ,umAuthListener);
               break;
           case R.id.back:
               startActivity(ZhuceActivity.class);
               break;
       }
    }
    UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            System.out.println("uid======="+map.get("uid"));
            System.out.println("name======="+map.get("name"));
            System.out.println("gender======="+map.get("gender"));
            System.out.println("iconurl======="+map.get("iconurl"));
            sp.edit().putString("icon",map.get("iconurl")).commit();
            sp.edit().putString("name",map.get("name")).commit();
            sp.edit().putString("gender",map.get("gender")).commit();
            startActivity(Main2Activity.class);
            finish();
            //ImageLoader.getInstance().displayImage(map.get("iconurl"), qqdl);
        }
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            if (UMShareAPI.get(LoginActivity.this).isInstall(LoginActivity.this, SHARE_MEDIA.QQ)) {
                Toast.makeText(LoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "no install QQ", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }

    };
    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);

    }
    @Override
    public void init() {
        StatusBarUtils.setStatus(this,true);
        sp=SharedPreferencesUtil.getPreferences();
        gengduo=findViewById(R.id.gengduo);
        gengduo.setOnClickListener(this);
        img=findViewById(R.id.back);
        img.setOnClickListener(this);
        qq=findViewById(R.id.qq);
        qq.setOnClickListener(this);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
    }
}
