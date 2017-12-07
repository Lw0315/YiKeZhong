package com.example.asus.yikezhong.presenter;

import java.util.List;

import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.model.PublishModel;
import com.example.asus.yikezhong.view.PublishView;

/**
 * Created by asus on 2017/11/28.
 */

public class PublishPresenter  extends BasePresenter<PublishView> implements PublishModel.Publish{
    private PublishModel publishModel;
    private PublishView publishView;


    public PublishPresenter(PublishView mView) {
        super(mView);
        publishModel=new PublishModel();
        this.publishView=mView;
        publishModel.setPublish(this);
    }

    public void duanzi(String uid,String content,List<String> path){
        publishModel.publish(uid,content,path);
    }
    @Override
    public void Publi(String bean) {
        publishView.PublicSuccess(bean);
    }
}
