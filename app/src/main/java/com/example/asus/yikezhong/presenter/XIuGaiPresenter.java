package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.model.XiuGaiCallBack;
import com.example.asus.yikezhong.model.XiuGaiModel;
import com.example.asus.yikezhong.view.XiuGaiView;

/**
 * Created by asus on 2017/11/29.
 */

public class XIuGaiPresenter extends BasePresenter<XiuGaiView> {
    private XiuGaiModel xiuGaiModel;
    private XiuGaiView xiuGaiView;
    public XIuGaiPresenter(XiuGaiView mView) {
        super(mView);
        xiuGaiModel=new XiuGaiModel();
        this.xiuGaiView=mView;
    }
    public void xiugai(String uid,String nickname){
        xiuGaiModel.xiugai(uid, nickname, new XiuGaiCallBack() {
            @Override
            public void Success(BaseResponseEntity baseResponseEntity) {
                xiuGaiView.XiuGaiSuccess(baseResponseEntity);

            }
        });
    }
}
