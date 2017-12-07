package com.example.asus.yikezhong.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.yikezhong.GeRenActivity;
import com.example.asus.yikezhong.R;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.presenter.ShouCangPresenter;
import com.example.asus.yikezhong.view.ShouCangView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import com.example.asus.yikezhong.adapter.ReMen_Adapter;
import com.example.asus.yikezhong.bean.Bean;
import com.example.asus.yikezhong.bean.ShiPinBean;
import com.example.asus.yikezhong.presenter.GetSpPresenter;
import com.example.asus.yikezhong.utils.GlideImage;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.GetSpView;

/**
 * Created by asus on 2017/11/15.
 */
public class Fragment_ReMen extends Fragment implements GetSpView, XRecyclerView.LoadingListener,ShouCangView {
    private View view;
    private Banner banner;
    private List<Integer> list;
    private XRecyclerView xrv;
    private List<Bean> slist;
    private View getview;
    private ReMen_Adapter ra;
    private GetSpPresenter getSpPresenter;
    private SharedPreferences sp;
    private int uid;
    private List<ShiPinBean.DataBean> listt;
    private List<ShiPinBean.DataBean> listtt;
    private int page=1;
    private int a=0;
    private ShouCangPresenter shouCangPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view = View.inflate(getActivity(), R.layout.fragment_remen, null);
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if(viewGroup!=null){
            viewGroup.removeView(view);
        }
        initview();
        initdata();
        return view;
    }

    private void initdata() {
        shouCangPresenter=new ShouCangPresenter(this);
        list=new ArrayList<>();
        slist=new ArrayList<>();
        listt=new ArrayList<>();
        listtt=new ArrayList<>();
        slist.add(new Bean("qwqw","wqwww00","wsss","fdfdff"));
        slist.add(new Bean("7885","422","wsss","1414"));
        slist.add(new Bean("2525","4252","5255","414"));
        list.add(R.mipmap.raw_1500258840);
        list.add(R.mipmap.raw_1500258881);
        list.add(R.mipmap.raw_1500258901);
        list.add(R.mipmap.raw_1500259026);
        banner.setImageLoader(new GlideImage());
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImages(list);
        banner.start();
    }
    private void initview() {
        sp= SharedPreferencesUtil.getPreferences();
        uid=sp.getInt("uid",0);
        getSpPresenter=new GetSpPresenter(this);
        getSpPresenter.getSp(uid+"","1",page+"");
        xrv=view.findViewById(R.id.xrv);
        getview=View.inflate(getActivity(),R.layout.head,null);
        banner=getview.findViewById(R.id.banner);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(this);
    }
    @Override
    public void Success(String msg) {

    }
    @Override
    public void Failure(String msg) {

    }

    @Override
    public void GetSpSuccess(ShiPinBean shiPinBean) {
       // List<ShiPinBean.DataBean> data = shiPinBean.getData();
        listt=shiPinBean.getData();
        listtt.addAll(listt);
        if(ra==null){
            xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
            ra=new ReMen_Adapter(getActivity(),listtt);
            xrv.setAdapter(ra);
            xrv.addHeaderView(getview);
        }else{
            ra.notifyDataSetChanged();
        }
        xrv.refreshComplete();
        xrv.loadMoreComplete();
        ra.setImageXing(new ReMen_Adapter.ImageXing() {
            @Override
            public void imageIconXing(View v, int p) {
                int wid = listtt.get(p).getWid();
                System.out.println("==========wid"+wid);
                shouCangPresenter.shoucang(uid+"",wid+"");
            }
        });
        ra.setImageXing1(new ReMen_Adapter.ImageXing1() {
            @Override
            public void imageIconXing1(View v, int p) {
                int wid = listtt.get(p).getWid();
                System.out.println("==========wid"+wid);
                shouCangPresenter.quxiao(uid+"",wid+"");
            }
        });
    }

    @Override
    public void onRefresh() {
        listtt.clear();
        getSpPresenter.getSp(uid+"","1","1");
    }

    @Override
    public void onLoadMore() {
        page++;
        getSpPresenter.getSp(uid+"","1",page+"");
    }

    @Override
    public void ShouCangSuccess(BaseResponseEntity baseResponseEntity) {
        System.out.println("=========="+baseResponseEntity.msg);
    }

    @Override
    public void QuxaioSuccess(BaseResponseEntity baseResponseEntity) {
        System.out.println("=========="+baseResponseEntity.msg);
    }
}
