package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.QuarterBean;
import com.example.asus.yikezhong.model.QuarterModel;
import com.example.asus.yikezhong.view.QuarterView;

/**
 * Created by asus on 2017/11/28.
 */

public class QuarterPresenter extends BasePresenter<QuarterView> implements QuarterModel.Quarter{
    private QuarterModel quarterModel;
    private QuarterView quarterView;
    public QuarterPresenter(QuarterView mView) {
        super(mView);
        quarterModel=new QuarterModel();
        this.quarterView=mView;
        quarterModel.setQuarter(this);

    }
    public void duanzi(String page){
        quarterModel.duanzi(page);
    }

    @Override
    public void DuanZi(QuarterBean bean) {
        quarterView.QuarterSuccess(bean);
    }
}
