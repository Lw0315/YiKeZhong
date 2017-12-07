package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.ShiPinBean;
import com.example.asus.yikezhong.model.GetSpCallBack;
import com.example.asus.yikezhong.model.GetSpModel;
import com.example.asus.yikezhong.view.GetSpView;

/**
 * Created by asus on 2017/12/1.
 */

public class GetSpPresenter extends BasePresenter<GetSpView> {
    private GetSpModel getSpModel;
    private GetSpView getSpView;
    public GetSpPresenter(GetSpView mView) {
        super(mView);
        getSpModel=new GetSpModel();
        this.getSpView=mView;
    }
    public void getSp(String uid,String type,String page){
        getSpModel.getSp(uid,type, page, new GetSpCallBack() {
            @Override
            public void getSpSuccess(ShiPinBean shiPinBean) {
                getSpView.GetSpSuccess(shiPinBean);
            }
        });
    }
}
