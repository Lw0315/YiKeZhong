package com.example.asus.yikezhong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.example.asus.yikezhong.base.BaseActivity;

import com.example.asus.yikezhong.base.BaseResponseEntity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import com.example.asus.yikezhong.presenter.XIuGaiPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.asus.yikezhong.service.ApiService;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.XiuGaiView;
import com.example.asus.yikezhong.zidingyi.View_Title;

public class UserMessageActivity extends BaseActivity<XIuGaiPresenter> implements XiuGaiView {
    private View_Title view_title;
    private ImageView login;
    private TextView nc;
    private static final int CHOOSE_PICTURE=0;
    private static final int TAKE_PICTURE=1;
    private static final int CROP_SMALL_PICTURE=2;
    private static Uri tempUri;
    private SharedPreferences sp;
    private int uid;
    @Override
    public int layoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    public XIuGaiPresenter getPresenter() {
        return new XIuGaiPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_message);
//    }

    @Override
    public void setMyClick(View view) {
       switch (view.getId()){
           case R.id.login:
               AlertDialog.Builder builder=new AlertDialog.Builder(this);
               builder.setTitle("设置头像");
               String[] items={"选择本地照片","拍照"};
               builder.setNegativeButton("取消",null);
               builder.setItems(items, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       switch (i){
                           case CHOOSE_PICTURE://选择本地照片
                               Intent open=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                               open.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                               startActivityForResult(open,CHOOSE_PICTURE);
                               break;
                           case TAKE_PICTURE://拍照
                               Intent openC=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                               tempUri=Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                               //指定照片保存路径sd卡，image.jpg为一个临时文件，每次拍照后这个图
                               openC.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                               startActivityForResult(openC,TAKE_PICTURE);
                               break;
                       }
                   }
               });
               builder.create().show();
               break;
           case R.id.nc:
               View vv= LayoutInflater.from(this).inflate(R.layout.xiugai,null);
               final EditText xg=vv.findViewById(R.id.xg);
               AlertDialog.Builder ab=new AlertDialog.Builder(this);
               ab.setMessage("修改一下昵称");
               ab.setView(vv);
               ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       String updata=xg.getText().toString().trim();
                       presenter.xiugai(uid+"",updata);
                       nc.setText(updata);

                   }
               });
               ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               ab.create();
               ab.show();
               break;
       }
    }
    /**
     * 保存裁剪之后的图片数据
     * @param data
     */
    protected void setImageToView(Intent data){
        Bundle extras=data.getExtras();
        if(extras!=null){
            Bitmap photo=extras.getParcelable("data");
            login.setImageBitmap(photo);
            saveImage(photo);
            File file=new File("mnt/sdcard/e.png");
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.zhaoapi.cn/").build();
            ApiService userBiz = retrofit.create(ApiService.class);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipartbody/form-data"),file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
            Call<ResponseBody> getdata = userBiz.getdata(uid + "", body);
            getdata.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    System.out.println("请求成功:"+response.toString());
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("请求失败："+t.toString());
                }
            });
        }
    }
    /**
     * 读取的图片存在一个路径里
     * @param photo
     */
    private void saveImage(Bitmap photo) {
        File file=new File("mnt/sdcard/e.png");
        try{
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bos);//压缩
            bos.flush();
            bos.close();
        }catch (Exception e){

        }
    }
    /**
     * 裁剪图片方法实现
     * @param uri
     */
    protected void startPhotoZoom(Uri uri){
        if(uri==null){
            Log.i("tag","The uri is not exist");
        }
        tempUri=uri;
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //aspectX aspectY是宽高的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //outputX outputY是裁剪图片的宽高
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,CROP_SMALL_PICTURE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                startPhotoZoom(data.getData());
                break;
            case CROP_SMALL_PICTURE:
                if(data!=null){
                    setImageToView(data);
                }
                break;
        }

    }
    @Override
    public void init() {
        sp= SharedPreferencesUtil.getPreferences();
        uid=sp.getInt("uid",0);
      view_title=findViewById(R.id.view_title);
      view_title.setText("用户信息");
      login=findViewById(R.id.login);
      nc=findViewById(R.id.nc);
      login.setOnClickListener(this);
      nc.setOnClickListener(this);
      view_title.setOnClickListenler(new View_Title.OnClickListener() {
          @Override
          public void ImageClicked(View v) {
              finish();
          }
      });
    }

    @Override
    public void Success(String msg) {

    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void XiuGaiSuccess(BaseResponseEntity baseResponseEntity) {
        System.out.println("====="+baseResponseEntity.msg);
        finish();

    }
}
