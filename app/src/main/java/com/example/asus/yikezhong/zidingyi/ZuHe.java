package com.example.asus.yikezhong.zidingyi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.yikezhong.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by asus on 2017/11/14.
 */

public class ZuHe extends LinearLayout implements View.OnClickListener{
    private TextView tv;
    private ImageView image;
    private SimpleDraweeView icon;
    public ZuHe(Context context) {
        this(context,null);
    }

    public ZuHe(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZuHe(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context,attrs);
    }

    private void initview(Context context, AttributeSet attrs) {
        View view= LayoutInflater.from(context).inflate(R.layout.tobar,this,true);
        tv=view.findViewById(R.id.tv);
        image=view.findViewById(R.id.image);
        icon=view.findViewById(R.id.icon);
        icon.setOnClickListener(this);
        image.setOnClickListener(this);
    }
    public void setImage(String s){
        icon.setImageURI(s);
    }
    public void setText(String s){
        tv.setText(s);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon:
                if (ClickListener!=null)
                ClickListener.ImageClicked(v);
                break;
            case R.id.image:
                if (ClickListener!=null)
                ClickListener.ImageClick(v);
                break;
        }

    }
    //定义接口的成员
    OnClickListener ClickListener;
    //接口的setter
    public void setOnClickListenler(OnClickListener ClickListener){
        //在setter中把这个接口的实现赋值给这个loginview的上面定义的接口
        this.ClickListener = ClickListener;
    }


    //接口
    public interface OnClickListener{
        public void ImageClicked(View v);//传的参数
        public void ImageClick(View v);
    }
}
