package com.example.asus.yikezhong;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.asus.yikezhong.utils.GlideLoader;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.asus.yikezhong.adapter.GridImageAdapter;
import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.presenter.ShiPinPresenter;
import com.example.asus.yikezhong.utils.FullyGridLayoutManager;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.ShiPinView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;
public class BianXieSPActivity extends BaseActivity<ShiPinPresenter> implements ShiPinView, AMapLocationListener {
    private List<LocalMedia> selectList = new ArrayList<>();
    private RecyclerView sp_xrv;
    private GridImageAdapter adapter;
    private TextView fabiao;
    private EditText et_content;
    private int maxSelectNum = 9;
    private SharedPreferences sp;
    private int uid;
    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 4;
    private double latitude;
    private double longitude;

    private ImageView fm;
    private Context context;

    private ArrayList<String> path = new ArrayList<>();
    private File file;
    @Override
    public int layoutId() {
        return R.layout.activity_bian_xie_sp;
    }
    @Override
    public ShiPinPresenter getPresenter() {
        return new ShiPinPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bian_xie_sp);
//    }
    @Override
    public void setMyClick(View view) {
        String content=et_content.getText().toString().trim();
        switch (view.getId()){
            case R.id.fabiao:
                String pa = selectList.get(0).getPath();
                presenter.shipin(uid+"",new File(pa),file,content,latitude+"",longitude+"");
                break;
            case R.id.fm:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        .singleSelect()
                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();
                ImageSelector.open(this, imageConfig); // 开启图片选择器
                break;
        }
    }
    public static final int REQUEST_CODE = 1000;
    @Override
    public void init() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_COARSE_LOCATION_REQUEST_CODE);
        }else {
            initMap();
        }
        sp= SharedPreferencesUtil.getPreferences();
        uid=sp.getInt("uid",0);
        sp_xrv=findViewById(R.id.sp_xrv);
        fabiao=findViewById(R.id.fabiao);
        fabiao.setOnClickListener(this);
        fm=findViewById(R.id.fm);
        fm.setOnClickListener(this);
        et_content=findViewById(R.id.et_content);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(BianXieSPActivity.this, 4, GridLayoutManager.VERTICAL, false);
        sp_xrv.setLayoutManager(manager);
        adapter = new GridImageAdapter(BianXieSPActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        sp_xrv.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(BianXieSPActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(BianXieSPActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(BianXieSPActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }
    private void initMap() {
        //声明mlocationClient对象
        AMapLocationClient mlocationClient;
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(BianXieSPActivity.this)
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
        }
    };

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
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;

            }
        }
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }
            path.clear();
            path.addAll(pathList);
            for (String s : path) {
                Glide.with(this).load(s).into(fm);
                file=new File(s);
            }
        }
    }
    @Override
    public void Success(String msg) {

    }
    @Override
    public void Failure(String msg) {

    }
    @Override
    public void ShiPinSuccess(BaseResponseEntity baseResponseEntity) {
        System.out.println("========"+baseResponseEntity.msg);
        startActivity(FBSuccessActivity.class);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                 latitude = aMapLocation.getLatitude();//获取纬度
                 longitude = aMapLocation.getLongitude();//获取经度
                System.out.println(latitude+""+longitude);
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表
                System.out.println("定位失败");
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
}
