package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.model.GuanZhuCallBack;
import com.example.asus.yikezhong.model.GuanZhuModel;
import com.example.asus.yikezhong.view.GuanZhuView;

/**
 * Created by asus on 2017/11/30.
 */

public class GuanZhuPresenter extends BasePresenter<GuanZhuView> {
    private GuanZhuModel guanZhuModel;
    private GuanZhuView guanZhuView;
    public GuanZhuPresenter(GuanZhuView mView) {
        super(mView);
        guanZhuModel=new GuanZhuModel();
        this.guanZhuView=mView;
    }
    public void guanzhu(String uid,String foll){
        guanZhuModel.guanzhu(uid, foll, new GuanZhuCallBack() {
            @Override
            public void GuanZhuSuccess(BaseResponseEntity msg) {
                guanZhuView.GuanZhu(msg);
            }
        });
    }
}
