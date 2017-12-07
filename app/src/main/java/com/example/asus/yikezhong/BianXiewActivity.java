package com.example.asus.yikezhong;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.asus.yikezhong.adapter.Adapter;
import com.example.asus.yikezhong.base.BaseActivity;
import com.example.asus.yikezhong.presenter.PublishPresenter;
import com.example.asus.yikezhong.utils.GlideLoader;
import com.example.asus.yikezhong.utils.SharedPreferencesUtil;
import com.example.asus.yikezhong.view.PublishView;

public class BianXiewActivity extends BaseActivity<PublishPresenter> implements PublishView {
    private TextView back, fabiao;
    private EditText et_content;
    private SharedPreferences sp;
    private int uid;
    private ImageView img_jia;
    private RecyclerView rxv;
    private Adapter adapter;

    private ArrayList<String> path = new ArrayList<>();
    private File file;
    @Override
    public int layoutId() {

        return R.layout.activity_bian_xiew;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted

            } else {
                // Permission Denied
                //  displayFrameworkBugMessageAndExit();
                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    public PublishPresenter getPresenter() {
        return new PublishPresenter(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bian_xiew);
//    }

    @Override
    public void setMyClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                //构建一个popupwindow的布局
                View view1 = View.inflate(this, R.layout.pop_duanzi, null);
                //创建PopupWindow对象，指定宽度和高度
                final PopupWindow pop_duanzi = new PopupWindow(view1, 600, 500, true);
                //弹入弹出动画
                pop_duanzi.setAnimationStyle(R.style.MyPopupWindow_anim_style);
                //弹框距离底部的距离
                pop_duanzi.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
                //弹框显示背景半透明
                backgroundAlpha(0.5f);
                //获取焦点
                pop_duanzi.setFocusable(true);
                pop_duanzi.update();//刷新
                TextView tv_pop_duanzi_1 = view1.findViewById(R.id.tv_pop_duanzi_1);
                TextView tv_pop_duanzi_2 = view1.findViewById(R.id.tv_pop_duanzi_2);
                TextView tv_pop_duanzi_3 = view1.findViewById(R.id.tv_pop_duanzi_3);
                //保存
                tv_pop_duanzi_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(Main2Activity.class);
                        finish();
                    }
                });
                //不保存
                tv_pop_duanzi_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(Main2Activity.class);
                        finish();
                    }
                });
                //取消
                tv_pop_duanzi_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //背景正常
                        backgroundAlpha(1.0f);
                        startActivity(Main2Activity.class);
                        pop_duanzi.dismiss();
                        finish();
                    }
                });
                break;
            case R.id.fabiao:
                String content = et_content.getText().toString().trim();
                presenter.duanzi(uid + "", content,path);
                break;
            case R.id.img_jia:
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
                        .mutiSelect()
//                        .crop()
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
    // popwindow背景半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0~1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void init() {
        // 防止键盘挡住输入框
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        sp = SharedPreferencesUtil.getPreferences();
        uid = sp.getInt("uid", 0);
        back = findViewById(R.id.back);
        fabiao = findViewById(R.id.fabiao);
        et_content = findViewById(R.id.et_content);
        back.setOnClickListener(this);
        fabiao.setOnClickListener(this);
        img_jia=findViewById(R.id.img_jia);
        rxv=findViewById(R.id.rv_image);
        img_jia.setOnClickListener(this);
        initdata();

    }

    private void initdata() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rxv.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        rxv.setAdapter(adapter);
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
            adapter.notifyDataSetChanged();

        }
    }



    @Override
    public void Success(String msg) {
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void PublicSuccess(String msg) {
        System.out.println("=========" + msg);
        startActivity(FBSuccessActivity.class);

    }
}
