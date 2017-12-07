package com.example.asus.yikezhong;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kson.slidingmenu.SlidingMenu;

import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.GetUserBean;
import com.example.asus.yikezhong.fragment.Fragment_Left;
import com.example.asus.yikezhong.fragment.Fragment_TuiJian;
import com.example.asus.yikezhong.fragment.Fragment_ShiPin;
import com.example.asus.yikezhong.fragment.Fragment_DuanZi;
import com.example.asus.yikezhong.presenter.GetUserPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.GetUserView;
import com.example.asus.yikezhong.zidingyi.ZuHe;

import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends BaseActivity implements GetUserView {
    private FrameLayout flt;
    private RelativeLayout rv1, rv2, rv3;
    private ImageView img1, img2, img3;
    private TextView tv1, tv2, tv3;
    private SlidingMenu menu;
    private Fragment_TuiJian tuijian;
    private Fragment_DuanZi duanzi;
    private Fragment_ShiPin shipin;
    private ZuHe view_title;
    private SharedPreferences sp;
    private GetUserPresenter gp;
    private int uid;

    @Override
    public int layoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gp.getUser(uid + "");
    }

    @Override
    public void setMyClick(View view) {
        switch (view.getId()) {
            case R.id.rv1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flt, tuijian).commit();
                img1.setImageResource(R.mipmap.raw_1500085367);
                img2.setImageResource(R.mipmap.raw_1500085327);
                img3.setImageResource(R.mipmap.raw_1500083686);
                tv1.setTextColor(getResources().getColor(R.color.colorbule));
                tv2.setTextColor(getResources().getColor(R.color.colorhui));
                tv3.setTextColor(getResources().getColor(R.color.colorhui));
                break;
            case R.id.rv2:
                getSupportFragmentManager().beginTransaction().replace(R.id.flt, duanzi).commit();
                tv2.setTextColor(getResources().getColor(R.color.colorbule));
                tv1.setTextColor(getResources().getColor(R.color.colorhui));
                tv3.setTextColor(getResources().getColor(R.color.colorhui));
                img1.setImageResource(R.mipmap.raw_1500083878);
                img2.setImageResource(R.mipmap.raw_1500085899);
                img3.setImageResource(R.mipmap.raw_1500083686);
                break;
            case R.id.rv3:
                getSupportFragmentManager().beginTransaction().replace(R.id.flt, shipin).commit();
                tv3.setTextColor(getResources().getColor(R.color.colorbule));
                tv1.setTextColor(getResources().getColor(R.color.colorhui));
                tv2.setTextColor(getResources().getColor(R.color.colorhui));
                img1.setImageResource(R.mipmap.raw_1500083878);
                img2.setImageResource(R.mipmap.raw_1500085327);
                img3.setImageResource(R.mipmap.raw_1500086067);
                break;
        }
    }

    @Override
    public void init() {
        gp = new GetUserPresenter(this);
        sp = SharedPreferencesUtil.getPreferences();
        uid=sp.getInt("uid",0);
        final String icon = sp.getString("icon", "");
        System.out.println("============" + icon);
        tuijian = new Fragment_TuiJian();
        duanzi = new Fragment_DuanZi();
        shipin = new Fragment_ShiPin();
        flt = findViewById(R.id.flt);
        rv1 = findViewById(R.id.rv1);
        rv2 = findViewById(R.id.rv2);
        rv3 = findViewById(R.id.rv3);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        rv1.setOnClickListener(this);
        rv2.setOnClickListener(this);
        rv3.setOnClickListener(this);
        view_title = findViewById(R.id.view_title);
        view_title.setText("推荐");
        view_title.setOnClickListenler(new ZuHe.OnClickListener() {
            @Override
            public void ImageClicked(View v) {
                menu.showMenu();
            }

            @Override
            public void ImageClick(View v) {
                startActivity(ChuangzuoActivity.class);
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.flt, tuijian).commit();
        img1.setImageResource(R.mipmap.raw_1500085367);
        tv1.setTextColor(getResources().getColor(R.color.colorbule));
        if(uid==0){
            startActivity(ZhuceActivity.class);
        }else{
            gp.getUser(uid+"");
        }
        initview();
    }
    private void initview() {
        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.left_fragment);
        //添加左菜单
        // setBehindContentView(R.layout.left_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.ft, new Fragment_Left()).commit();
        //设置slidingmenu相关属性
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }
    @Override
    public void GetUser(GetUserBean bean) {
        System.out.println("===="+bean.getMsg());
        GetUserBean.DataBean data = bean.getData();
        final Object icon = data.getIcon();
        if(icon.equals("")){
            view_title.setImage(String.valueOf(R.mipmap.raw_1499936862));
        }else{
            view_title.setImage((String) data.getIcon());
        }
      EventBus.getDefault().postSticky(bean);
    }

}