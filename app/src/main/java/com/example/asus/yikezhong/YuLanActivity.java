package com.example.asus.yikezhong;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.icu.text.MessagePattern;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.example.asus.yikezhong.FBSuccessActivity;
import com.example.asus.yikezhong.R;
import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.base.BasePresenter;
import com.example.asus.yikezhong.base.BaseResponseEntity;
import com.example.asus.yikezhong.presenter.ShiPinPresenter;
import com.example.asus.yikezhong.utils.GlideLoader;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.ShiPinView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YuLanActivity extends BaseActivity<ShiPinPresenter> implements ShiPinView,AMapLocationListener {
    private ImageView iv_tihuan;
    private ArrayList<String> path = new ArrayList<>();
    private File file;
    private EditText et_conter;
    private Button bt_fb;
    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 4;
    private double latitude;
    private double longitude;
    private SharedPreferences sp;
    private Bitmap bitmap;
    private int width,height;
    private LinearLayout yuantu,laotupian,dipian,fudiao;
    private EditText[] ets = new EditText[20];
    private float[] colorMatrix = new float[20];
    private int uid;
    @Override
    public int layoutId() {
        return R.layout.activity_yu_lan;
    }
    @Override
    public ShiPinPresenter getPresenter() {
        return new ShiPinPresenter(this);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_yu_lan);
//    }

    @Override
    public void setMyClick(View view) {
        String content=et_conter.getText().toString().trim();

       switch (view.getId()){
           case R.id.iv_tihuan:
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
           case R.id.bt_fb:
               Intent intent=getIntent();
               String url = intent.getStringExtra("url");
               System.out.println("===="+url);
               presenter.shipin(uid+"",new File(url),file,content,latitude+"",longitude+"");
               break;
           case R.id.yuantu:
               initMatrix();
               setBitmap();
               break;
           case R.id.laotupian:
               setOldBitmap();
               break;
           case R.id.dipian:
               setInvertImage();
               break;
           case R.id.fudiao:
               setEmbossImage();
               break;
       }
    }
    private void setInvertImage(){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int[] oldPixs = new int[width*height];
        int[] newPixs = new int[width*height];
        bitmap.getPixels(oldPixs, 0, width, 0, 0, width, height);
        int color;
        int r,g,b;
        int r1,g1,b1,a;
        for (int i = 0; i < oldPixs.length; i++) {
            color = oldPixs[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r1 = 255 - r;
            g1 = 255 - g;
            b1 = 255 - b;
            newPixs[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPixs, 0, width, 0, 0, width, height);
        iv_tihuan.setImageBitmap(bmp);
    }
    private void setEmbossImage(){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int[] oldPixs = new int[width*height];
        int[] newPixs = new int[width*height];
        bitmap.getPixels(oldPixs, 0, width, 0, 0, width, height);
        int color,oldColor;
        int r,g,b;
        int r0,g0,b0,a0;
        int r1,g1,b1,a;
        for (int i = 1; i < oldPixs.length; i++) {
            oldColor = oldPixs[i-1];
            r0 = Color.red(oldColor);
            g0 = Color.green(oldColor);
            b0 = Color.blue(oldColor);
            a0 = Color.alpha(oldColor);
            color = oldPixs[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r1 = r0 - r + 127;
            if (r1<0) {
                r1 = 0;
            }else if(r1>255){
                r1 = 255;
            }
            g1 = g0 - g + 127;
            if (g1<0) {
                g1 = 0;
            }else if(g1>255){
                g1 = 255;
            }
            b1 = b0 - b + 127;
            if (b1<0) {
                b1 = 0;
            }else if(b1>255){
                b1 = 255;
            }
            newPixs[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPixs, 0, width, 0, 0, width, height);
        iv_tihuan.setImageBitmap(bmp);
    }
    private void initMatrix(){
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                ets[i].setText(1+"");
                colorMatrix[i] = 1.0f;
            }else {
                ets[i].setText(0+"");
                colorMatrix[i] = 0.0f;
            }
        }
    }
    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            colorMatrix[i] = Float.valueOf(ets[i].getText().toString());
        }
    }
    private void setBitmap(){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix matrix = new ColorMatrix(colorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        iv_tihuan.setImageBitmap(bmp);
    }
    private void setOldBitmap(){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int[] oldPixs = new int[width*height];
        int[] newPixs = new int[width*height];
        bitmap.getPixels(oldPixs, 0, width, 0, 0, width, height);
        int color;
        int r,g,b;
        int r1,g1,b1,a;
        for (int i = 0; i < oldPixs.length; i++) {
            color = oldPixs[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r1 = (int) (0.393*r+0.769*g + 0.189*b);
            g1 = (int) (0.349*r + 0.686*g + 0.168*b);
            b1 = (int) (0.272*r + 0.534*g + 0.131*b);
            r1 = r1 > 255 ? 255:r1;
            g1 = g1 > 255 ? 255:g1;
            b1 = b1 > 255 ? 255:b1;
            newPixs[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPixs, 0, width, 0, 0, width, height);
        iv_tihuan.setImageBitmap(bmp);
    }
    public static final int REQUEST_CODE = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }
            path.clear();
            path.addAll(pathList);
            for (String s : path) {
                Glide.with(this).load(s).into(iv_tihuan);
                file=new File(s);
                bitmap = BitmapFactory.decodeFile(s);
                width = bitmap.getWidth();
                height = bitmap.getHeight();
            }
        }
    }

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
        iv_tihuan=findViewById(R.id.iv_tihuan);
        iv_tihuan.setOnClickListener(this);
        bt_fb=findViewById(R.id.bt_fb);
        bt_fb.setOnClickListener(this);
        et_conter=findViewById(R.id.et_conter);
        yuantu=findViewById(R.id.yuantu);
        laotupian=findViewById(R.id.laotupian);
        dipian=findViewById(R.id.dipian);
        fudiao=findViewById(R.id.fudiao);
        yuantu.setOnClickListener(this);
        laotupian.setOnClickListener(this);
        dipian.setOnClickListener(this);
        fudiao.setOnClickListener(this);

      //  et_conter.setInputType(InputType.TYPE_CLASS_NUMBER);

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
}
