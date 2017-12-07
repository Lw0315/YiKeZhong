package com.example.asus.yikezhong.zidingyi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.Nullable;
import com.example.asus.yikezhong.R;

/**
 * Created by asus on 2017/11/25.
 */

public class View_Title  extends LinearLayout implements View.OnClickListener{
    private TextView tv,back;
    public View_Title(Context context) {
        this(context,null);
    }

    public View_Title(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public View_Title(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context,attrs);
    }

    private void initview(Context context, AttributeSet attrs) {
        View view= LayoutInflater.from(context).inflate(R.layout.tobarr,this,true);
        tv=view.findViewById(R.id.tv);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(this);
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
            case R.id.back:
                ClickListener.ImageClicked(v);
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
    }
}
