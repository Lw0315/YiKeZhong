package com.example.asus.yikezhong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 2017/11/14.
 */

public abstract  class BaseFragment extends Fragment {
    private View view;
    public abstract int layoutId();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(),layoutId(),null);
        return view;
    }
}
