package com.example.asus.yikezhong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.yikezhong.adapter.GeRenAdapter;
import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.bean.GetUserBean;
import com.example.asus.yikezhong.bean.GetUserShiPin;
import com.example.asus.yikezhong.bean.ShiPinBean;
import com.example.asus.yikezhong.presenter.GetUserPresenter;
import com.example.asus.yikezhong.presenter.GetUserSpPresenter;
import com.example.asus.yikezhong.presenter.GuanZhuPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.GetSpView;
import com.example.asus.yikezhong.view.GetUserSpView;
import com.example.asus.yikezhong.view.GetUserView;
import com.example.asus.yikezhong.view.GuanZhuView;
import com.example.asus.yikezhong.zidingyi.RundImageView;

import java.util.ArrayList;
import java.util.List;

public class GeRenActivity extends BaseActivity<GetUserSpPresenter> implements GetUserView,GetUserSpView,GuanZhuView {
    private TextView tv_wangming,fensi,guanzhu;
    private RundImageView img_icon;
    private GetUserPresenter gp;
    private RecyclerView xrv_geren;
    private TextView zuopin;
    private GeRenAdapter ga;
    private List<GetUserShiPin.DataBean> listt;
    private List<GetUserShiPin.DataBean> listtt;
    private SharedPreferences sp;
    private String suid;
    private Object icon;
    private String nickname;
    private Button jiaguanzhu;
    private int a=0;
    private GuanZhuPresenter guanZhuPresenter;
    @Override
    public int layoutId() {
        return R.layout.activity_ge_ren;
    }
    @Override
    public GetUserSpPresenter getPresenter() {
        return new GetUserSpPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ge_ren);
//    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void setMyClick(View view) {
        switch (view.getId()){
            case R.id.jiaguanzhu:
                a++;
                if(a%2==0){
                   jiaguanzhu.setSelected(false);
                   jiaguanzhu.setText("+  关注");
                }else{
                    sp=SharedPreferencesUtil.getPreferences();
                    int uid = sp.getInt("uid", 0);
                    jiaguanzhu.setSelected(true);
                    jiaguanzhu.setText("已关注");
                    jiaguanzhu.setTextColor(R.color.white);
                    guanZhuPresenter.guanzhu(uid+"",suid);
                }
                break;
        }
    }
    @Override
    public void init() {
        guanZhuPresenter=new GuanZhuPresenter(this);
        gp=new GetUserPresenter(this);
        Bundle bundle = getIntent().getExtras();   //得到传过来的bundle
        suid= bundle.getString("suid");
        System.out.println("=======ssssss"+suid);
        gp.getUser(suid);
        tv_wangming=findViewById(R.id.tv_wangming);
        img_icon=findViewById(R.id.img_icon);
        fensi=findViewById(R.id.fensi);
        guanzhu=findViewById(R.id.guanzhu);
        xrv_geren=findViewById(R.id.xrv_geren);
        zuopin=findViewById(R.id.zuopin);
        jiaguanzhu=findViewById(R.id.jiaguanzhu);
        jiaguanzhu.setOnClickListener(this);
        initdata();
    }
    private void initdata() {
        listt=new ArrayList<>();
        listtt=new ArrayList<>();
    }
    @Override
    public void GetUser(GetUserBean bean) {
        GetUserBean.DataBean data = bean.getData();
        System.out.println("====namee"+data.getNickname());
        icon = data.getIcon();
        nickname = data.getNickname();
        Glide.with(this).load(data.getIcon()).into(img_icon);
        tv_wangming.setText(data.getNickname());
        if(data.getFans()==0){
         fensi.setText("0");
        }else{
            fensi.setText(data.getFans());
        }
         if(data.getFollow()==0){
            guanzhu.setText("0");
         }else{
             guanzhu.setText(data.getFollow());
         }
        String token = data.getToken();
        sp= SharedPreferencesUtil.getPreferences();
        sp.edit().putString("token",token).commit();
        presenter.getusersp(suid,"1");
    }
    @Override
    public void Success(String msg) {

    }
    @Override
    public void Failure(String msg) {

    }
    @Override
    public void GetUserSpSuccess(GetUserShiPin getUserShiPin) {
        listt=getUserShiPin.getData();
        listtt.addAll(listt);
      //  List<GetUserShiPin.DataBean> data = getUserShiPin.getData();
        zuopin.setText("作品("+ listtt.size()+"）");
        ga=new GeRenAdapter(this,listtt,icon,nickname);
        xrv_geren.setLayoutManager(new LinearLayoutManager(this));
        xrv_geren.setAdapter(ga);
        sp.edit().putString("token","4860E93D56838926DD61E21884D6939D").commit();
    }

    @Override
    public void GuanZhu(BaseResponseEntity msg) {
        System.out.println("========="+msg.msg);
    }
}
