package com.example.asus.yikezhong.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.asus.yikezhong.R;

import java.lang.reflect.Field;
import com.example.asus.yikezhong.adapter.MyAdapter;
import com.example.asus.yikezhong.zidingyi.ZuHe;

/**
 * Created by asus on 2017/11/15.
 */
public class Fragment_TuiJian extends Fragment {
    private View view;
    private ZuHe view_title;
    private TabLayout tab;
    private ViewPager vp;
    private MyAdapter ma;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_tuijian,null);
        initview();
        initdata();
        MysetIndicator(tab,40,40);
        return view;
    }
    private void initdata() {

    }
    private void initview() {
      tab=view.findViewById(R.id.tab);
      vp=view.findViewById(R.id.vp);
      tab.setupWithViewPager(vp);
      ma=new MyAdapter(getChildFragmentManager());
      vp.setAdapter(ma);

    }
    //设置下划线的长度
    public void MysetIndicator(TabLayout tabs,int leftDip,int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

}
