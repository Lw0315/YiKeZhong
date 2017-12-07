package com.example.asus.yikezhong;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/12/6.
 */

public class BianXieShiPin extends BaseActivity {
    private ImageView img,anjian;
    private RelativeLayout rl;
    private Button queding;
    private List<LocalMedia> selectList = new ArrayList<>();
    private double latitude;
    private double longitude;
    private PlayerView playerView;
    @Override
    public int layoutId() {
        return R.layout.bianxiesp;
    }
    @Override
    public BasePresenter getPresenter() {
        return null;
    }
    @Override
    public void setMyClick(View view) {
      switch (view.getId()){
    case R.id.anjian:
        PictureSelector.create(BianXieShiPin.this)
                .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .previewVideo(true)// 是否可预览视频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .glideOverride(400,400)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3,2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .openClickSound(false)// 是否开启点击声音 true or false
                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                .videoQuality(1)// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(1)// 显示多少秒以内的视频or音频也可适用 int
                .recordVideoSecond(20)//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        break;
          case R.id.queding:
              playerView.stopPlay();
              Intent intent=new Intent(BianXieShiPin.this, YuLanActivity.class);
              intent.putExtra("url",selectList.get(0).getPath());
              startActivity(intent);
              break;
      }
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }

                    break;
            }
        }
    }
    @Override
    public void init() {
       img=findViewById(R.id.img);
        Glide.with(this).load(R.mipmap.malu).into(img);
       anjian=findViewById(R.id.anjian);
       anjian.setOnClickListener(this);
       rl=findViewById(R.id.rl);
       queding=findViewById(R.id.queding);
       queding.setOnClickListener(this);
    }
}
