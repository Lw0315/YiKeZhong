package com.example.asus.yikezhong.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.asus.yikezhong.fragment.Fragment_GuanZhu;
import com.example.asus.yikezhong.fragment.Fragment_ReMen;

/**
 * Created by asus on 2017/11/25.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] title={"热门","关注"};


    public MyAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override

    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new Fragment_ReMen();
                break;
            case 1:
                fragment=new Fragment_GuanZhu();
                break;
       }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
