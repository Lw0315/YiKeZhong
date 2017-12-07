package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.model.ShouCangCallBack;
import com.example.asus.yikezhong.model.ShouCangModel;
import com.example.asus.yikezhong.view.ShouCangView;

/**
 * Created by asus on 2017/12/7.
 */

public class ShouCangPresenter extends BasePresenter<ShouCangView> {
    private ShouCangModel shouCangModel;
    private ShouCangView shouCangView;
    public ShouCangPresenter(ShouCangView mView) {
        super(mView);
        shouCangModel=new ShouCangModel();
        this.shouCangView=mView;
    }
    public void shoucang(String uid,String wid){
        shouCangModel.shoucang(uid, wid, new ShouCangCallBack() {
            @Override
            public void ShouCangSuccess(BaseResponseEntity baseResponseEntity) {
                shouCangView.ShouCangSuccess(baseResponseEntity);
            }
        });
    }
    public void quxiao(String uid,String wid){
        shouCangModel.quxiao(uid, wid, new ShouCangCallBack() {
            @Override
            public void ShouCangSuccess(BaseResponseEntity baseResponseEntity) {
                shouCangView.QuxaioSuccess(baseResponseEntity);
            }
        });
    }
}
