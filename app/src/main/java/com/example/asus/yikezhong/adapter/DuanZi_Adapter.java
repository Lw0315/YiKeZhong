package com.example.asus.yikezhong.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.yikezhong.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import com.example.asus.yikezhong.bean.QuarterBean;

/**
 * Created by asus on 2017/11/28.
 */

public class DuanZi_Adapter extends RecyclerView.Adapter<DuanZi_Adapter.DuanZi_Holder> {
    private Context context;
    private List<QuarterBean.DataBean> list;
    private boolean isOpen = false;
    private DuanZi_AAdapter daa;
    public DuanZi_Adapter(Context context, List<QuarterBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DuanZi_Adapter.DuanZi_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.duanzi_item, null);
        DuanZi_Holder myholder = new DuanZi_Holder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(final DuanZi_Adapter.DuanZi_Holder holder, final int position) {
        QuarterBean.DataBean dataBean = list.get(position);
        holder.content.setText(list.get(position).getContent());
        holder.time.setText(list.get(position).getCreateTime());
        QuarterBean.DataBean.UserBean user = list.get(position).getUser();
        holder.img.setImageURI(user.getIcon());
        holder.wangming.setText(user.getNickname());
        final int folluid = list.get(position).getUid();
        String imgUrls = dataBean.getImgUrls();
        if (imgUrls !=null) {
            String[] s=imgUrls.split("\\|");
           int length=s.length;
           if(length==1){
               daa = new DuanZi_AAdapter(context,s);
               holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
               holder.recyclerView.setAdapter(daa);

           }else if(length==2||length==4){
               daa = new DuanZi_AAdapter(context,s);
               holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
               holder.recyclerView.setAdapter(daa);
           }else{
               daa = new DuanZi_AAdapter(context,s);
               holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
               holder.recyclerView.setAdapter(daa);
           }

        }
        holder.menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float menu_addx = holder.menu_add.getX();
                final float ly = holder.ly.getX();
                final float fx = holder.fx.getX();
                final float sc = holder.sc.getX();
                holder.sc.setX(sc);
                holder.fx.setX(fx);
                holder.ly.setX(ly);
                if (!isOpen) {
                    holder.ly.setVisibility(View.VISIBLE);
                    holder.fx.setVisibility(View.VISIBLE);
                    holder.sc.setVisibility(View.VISIBLE);
                    holder.iv_isopenMenu.animate().rotation(360).setDuration(800).start();
                    holder.iv_isopenMenu.setImageResource(R.mipmap.jian);
                    Log.e("qqq", holder.fx.getTranslationX() + "");
//           注意这里的translationX要回到原始位置
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.ly, "translationX", 0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.fx, "translationX", 0);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.sc, "translationX", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(400);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    isOpen = true;
                } else {
                    holder.iv_isopenMenu.animate().rotation(0).setDuration(800).start();
                    holder.iv_isopenMenu.setImageResource(R.mipmap.jia);
                  //          注意这里的translationX要运行到plus后面
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.ly, "translationX", menu_addx - sc);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.fx, "translationX", menu_addx - fx);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.sc, "translationX", menu_addx - ly);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(400);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            holder.ly.setVisibility(View.GONE);
                            holder.fx.setVisibility(View.GONE);
                            holder.sc.setVisibility(View.GONE);
                        }
                    });
                    isOpen=false;
                }
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       iconClick.onSetIconClick(v,position);
                      // holder.zan.setImageResource(R.mipmap.hongxin);

                    }
                });


            }
        });
    }
    public ImageOnClick onClick;
    public interface ImageOnClick{
        void OnSetClick(View v,int p);
    }

    public void setOnClick(ImageOnClick onClick) {
        this.onClick = onClick;
    }
    public IconClick iconClick;
    public interface IconClick{
        void onSetIconClick(View v,int p);
    }

    public void setIconClick(IconClick iconClick) {
        this.iconClick = iconClick;
    }

    @Override
    public int getItemCount () {
        return list.size();
    }
    public class DuanZi_Holder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView wangming, time, content;
        private LinearLayout menu_add, ly, fx, sc;
        private ImageView iv_isopenMenu,zan,fenxiang,xinxi;
        private RecyclerView recyclerView;
        public DuanZi_Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            wangming = itemView.findViewById(R.id.wangming);
            time = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.content);
            menu_add = itemView.findViewById(R.id.menu_add);
            ly = itemView.findViewById(R.id.ly);
            fx = itemView.findViewById(R.id.fx);
            sc = itemView.findViewById(R.id.sc);
            iv_isopenMenu = itemView.findViewById(R.id.iv_isopenMenu);
            zan=itemView.findViewById(R.id.zan);
            fenxiang=itemView.findViewById(R.id.fenxiang);
            recyclerView=itemView.findViewById(R.id.recycle);
        }
    }
}