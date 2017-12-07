package com.example.asus.yikezhong.presenter;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.bean.GetUserShiPin;
import com.example.asus.yikezhong.model.GetUserSpCallBack;
import com.example.asus.yikezhong.model.GetUserSpModel;
import com.example.asus.yikezhong.view.GetUserSpView;

/**
 * Created by asus on 2017/12/6.
 */

public class GetUserSpPresenter extends BasePresenter<GetUserSpView> {
    private GetUserSpModel spModel;
    private GetUserSpView spView;
    public GetUserSpPresenter(GetUserSpView mView) {
        super(mView);
        spModel=new GetUserSpModel();
        this.spView=mView;
    }
    public void getusersp(String uid,String page){
        spModel.getusersp(uid, page, new GetUserSpCallBack() {
            @Override
            public void GetUserSp(GetUserShiPin msg) {
                spView.GetUserSpSuccess(msg);
            }
        });
    }
}
