package com.example.asus.yikezhong.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.asus.yikezhong.GeRenActivity;
import com.example.asus.yikezhong.Main2Activity;
import com.example.asus.yikezhong.R;
import com.example.asus.yikezhong.utils.ObservableScrollView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.asus.yikezhong.adapter.DuanZi_Adapter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.bean.QuarterBean;
import com.example.asus.yikezhong.presenter.GuanZhuPresenter;
import com.example.asus.yikezhong.presenter.QuarterPresenter;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.GuanZhuView;
import com.example.asus.yikezhong.view.QuarterView;
import com.example.asus.yikezhong.zidingyi.ZuHe;

/**
 * Created by asus on 2017/11/15.
 */
public class Fragment_DuanZi extends Fragment implements QuarterView, XRecyclerView.LoadingListener, ObservableScrollView.ScrollViewListener, ViewTreeObserver.OnGlobalLayoutListener {
    private View view;
    private ZuHe view_title;
    private XRecyclerView xrv;
    private QuarterPresenter qp;
    private DuanZi_Adapter da;
    private int page=1;
    private List list=new ArrayList();
    private List<QuarterBean.DataBean> data;
    private List<QuarterBean.DataBean> data2;
   // private GuanZhuPresenter pp;
    private SharedPreferences sp;
    private int imageHeight;
    private int uid;
    private ObservableScrollView scrollView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = View.inflate(getActivity(), R.layout.fragment_duanzi, null);

        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }
       // com.example.asus.yikezhong.view=View.inflate(getActivity(),R.layout.fragment_duanzi,null);
        view_title=getActivity().findViewById(R.id.view_title);
        view_title.setText("段子");
        sp= SharedPreferencesUtil.getPreferences();
       // pp=new GuanZhuPresenter(this);
        uid=sp.getInt("uid",0);
        initview();
        initListeners();
        return view;
    }
    private void initListeners() {
        ViewTreeObserver vto =xrv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(this);
    }
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        // Log.i("TAG", "y--->" + y + "    height-->" + height);
        if (y <= 0) {
            view_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imageHeight) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            view_title.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {
            view_title.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }
    /* @Override
    public void onResume() {
        super.onResume();
        qp=new QuarterPresenter(this);
        qp.duanzi(page+"");
    }*/

    private void initview() {
        data2=new ArrayList<>();
        scrollView=view.findViewById(R.id.scrollView);
        xrv=view.findViewById(R.id.xrv);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(this);
        qp=new QuarterPresenter(this);
        qp.duanzi(page+"");

    }

    @Override
    public void Success(String msg) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void QuarterSuccess(final QuarterBean quarterBean) {
       // final List<QuarterBean.DataBean> data = quarterBean.getData();
//        System.out.println("data======"+data.size());
            data = quarterBean.getData();
            data2.addAll(data);
            if(da==null)
            {
                da=new DuanZi_Adapter(getActivity(),data2);
                xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
                xrv.setAdapter(da);
            }
            else {
                da.notifyDataSetChanged();
            }

        xrv.refreshComplete();
        xrv.loadMoreComplete();
        da.setIconClick(new DuanZi_Adapter.IconClick() {
            @Override
            public void onSetIconClick(View v, int p) {
                Intent intent=new Intent(getActivity(), GeRenActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onRefresh() {
        data2.clear();
        qp.duanzi("1");
    }

    @Override
    public void onLoadMore() {
        page++;
        qp.duanzi(page+"");

    }

//    @Override
//    public void GuanZhu(BaseResponseEntity msg) {
//        System.out.println("=========msg"+msg.msg);
//    }

    @Override
    public void onGlobalLayout() {
        xrv.getViewTreeObserver().removeGlobalOnLayoutListener(
                this);
        imageHeight = xrv.getHeight();
        scrollView.setScrollViewListener(this);
    }
}
