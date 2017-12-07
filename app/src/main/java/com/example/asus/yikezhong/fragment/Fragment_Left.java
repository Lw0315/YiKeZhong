package com.example.asus.yikezhong.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.asus.yikezhong.LoginActivity;
import com.example.asus.yikezhong.MyGuanZhuActivity;
import com.example.asus.yikezhong.R;
import com.example.asus.yikezhong.SheZhiActivity;
import com.example.asus.yikezhong.UserMessageActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import com.example.asus.yikezhong.bean.GetUserBean;
import com.example.asus.yikezhong.presenter.GetUserPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.GetUserView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by asus on 2017/11/23.
 */

public class Fragment_Left extends Fragment implements View.OnClickListener{
    private View view;
    private SimpleDraweeView icon;
    private ImageView iv_left_zuopin,iv_left_shezhi,iv_left_ye2;
    private SharedPreferences sp;
    private TextView tv_name;
    private ImageView img_sex;
    private RelativeLayout rl;
    private String nickname;
    private RelativeLayout rl_guanzhu;
    private Object iconn;
    private Switch st;
    private ImageView iv_left_ye1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = View.inflate(getActivity(), R.layout.left_item, null);
       }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }
        initview();
        EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
   public void Event(GetUserBean bean){
        GetUserBean.DataBean data = bean.getData();
        System.out.println("====data"+data.getNickname());
       nickname= data.getNickname();
       iconn=data.getIcon();
        if(!data.getNickname().equals("null")){
            tv_name.setText(data.getNickname());
        }else{
            tv_name.setText(data.getMobile());
        }
        if(!data.getIcon().equals("null")){
            icon.setImageURI((String) data.getIcon());
        }else{
            icon.setImageURI(String.valueOf(R.mipmap.raw_1499936862));
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void initview() {
       sp= SharedPreferencesUtil.getPreferences();
        String iconn = sp.getString("icon", "");
        String name = sp.getString("name", "");
        String gender = sp.getString("gender", "");
        icon =view.findViewById(R.id.icon);
        icon.setOnClickListener(this);
        rl=view.findViewById(R.id.rl);
        rl.setOnClickListener(this);
        iv_left_zuopin=view.findViewById(R.id.iv_left_zuopin);
        iv_left_shezhi=view.findViewById(R.id.iv_left_shezhi);
        iv_left_shezhi.setOnClickListener(this);
        rl_guanzhu=view.findViewById(R.id.rl_guanzhu);
        iv_left_ye2=view.findViewById(R.id.iv_left_ye2);
        iv_left_ye1=view.findViewById(R.id.iv_left_ye1);
        rl_guanzhu.setOnClickListener(this);
        icon.setImageURI(iconn);
        tv_name=view.findViewById(R.id.tv_name);
        tv_name.setText(name);
        img_sex=view.findViewById(R.id.img_sex);
        if(gender.equals("女")){
            img_sex.setImageResource(R.mipmap.sex);
        }else if(gender.equals("男")){
            img_sex.setImageResource(R.mipmap.nan);
        }
        st=view.findViewById(R.id.st);
        st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    iv_left_ye1.setSelected(true);
                }else{
                    iv_left_ye1.setSelected(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl:
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.iv_left_shezhi:
                Intent intent1=new Intent(getActivity(), SheZhiActivity.class);
                startActivity(intent1);
                break;
            case R.id.icon:
                Intent intent2=new Intent(getActivity(), UserMessageActivity.class);
                intent2.putExtra("nickname",nickname);
                intent2.putExtra("icon", (String)(iconn));
                startActivity(intent2);
                break;
            case R.id.rl_guanzhu:
                Intent intent3=new Intent(getActivity(), MyGuanZhuActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
