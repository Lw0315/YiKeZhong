package com.example.asus.yikezhong.presenter;

import java.io.File;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.model.ShiPinCallBack;
import com.example.asus.yikezhong.model.ShiPinModel;
import com.example.asus.yikezhong.view.ShiPinView;

/**
 * Created by asus on 2017/12/1.
 */

public class ShiPinPresenter extends BasePresenter<ShiPinView> {
    private ShiPinModel shiPinModel;
    private ShiPinView shiPinView;
    public ShiPinPresenter(ShiPinView mView) {
        super(mView);
        shiPinModel=new ShiPinModel();
        this.shiPinView=mView;
    }
    public void shipin(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude){
       shiPinModel.ShiPin(uid, videoFile, coverFile, videoDesc, latitude, longitude, new ShiPinCallBack() {
           @Override
           public void SuccessShiPin(BaseResponseEntity baseResponseEntity) {
               shiPinView.ShiPinSuccess(baseResponseEntity);
           }
       });
    }

}
