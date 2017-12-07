package com.example.asus.yikezhong.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yikezhong.R;

import com.example.asus.yikezhong.zidingyi.ZuHe;

/**
 * Created by asus on 2017/11/15.
 */
public class Fragment_ShiReMen extends Fragment {
    private View view;
    private ZuHe view_title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=View.inflate(getActivity(),R.layout.fragment_duanzi,null);
        view_title=getActivity().findViewById(R.id.view_title);
        view_title.setText("视频");
        return view;
    }
}
