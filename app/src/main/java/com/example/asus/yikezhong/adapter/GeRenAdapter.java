package com.example.asus.yikezhong.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.asus.yikezhong.R;
import com.example.asus.yikezhong.bean.GetUserShiPin;
import com.example.asus.yikezhong.bean.ShiPinBean;
import com.example.asus.yikezhong.zidingyi.RotateTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by asus on 2017/12/6.
 */

public class GeRenAdapter extends RecyclerView.Adapter<GeRenAdapter.GeRenHolder>  {
    private Activity context;
    private List<GetUserShiPin.DataBean> list;
    private PlayerView play;
    private boolean isOpen = false;
    private Object icon;
    private String nickname;
    public GeRenAdapter(Activity context, List<GetUserShiPin.DataBean> list,Object icon,String nickname) {
        this.context = context;
        this.list = list;
        this.icon=icon;
        this.nickname=nickname;
    }
    @Override
    public GeRenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.geren_item,parent,false);
        GeRenAdapter.GeRenHolder geRenHolder=new GeRenAdapter.GeRenHolder(view);
        return geRenHolder;

    }

    @Override
    public void onBindViewHolder(final GeRenHolder holder, final int position) {
        holder.ceshi.setDegrees(10);
        holder.ceshi.setText("神评");
      //  ShiPinBean.DataBean.UserBean user = list.get(position).);
        holder.img.setImageURI(String.valueOf(icon));
        holder.tv_wangming.setText(nickname);
        holder.tv_time.setText(list.get(position).getCreateTime());
        holder.tv_content.setText(list.get(position).getWorkDesc());
        View player=View.inflate(context,R.layout.simple_player_view_player,holder.shipin_rl);
       String url =list.get(position).getVideoUrl();
       String replace = url.replace("https://www.zhaoapi.cn", "http://120.27.23.105");
        new PlayerView(context,player)
                .setTitle(list.get(position).getWorkDesc())
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context)
                                .load(list.get(position).getCover())
                                .into(ivThumbnail);

                    }
                })
                .setPlaySource(replace)
                .startPlay();
        holder.menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float menu_addx = holder.menu_add.getX();
                final float ly = holder.ly.getX();
                final float fx = holder.fxx.getX();
                final float sc = holder.sc.getX();
                holder.sc.setX(sc);
                holder.fxx.setX(fx);
                holder.ly.setX(ly);
                if (!isOpen) {
                    holder.ly.setVisibility(View.VISIBLE);
                    holder.fxx.setVisibility(View.VISIBLE);
                    holder.sc.setVisibility(View.VISIBLE);
                    holder.iv_isopenMenu.animate().rotation(360).setDuration(800).start();
                    holder.iv_isopenMenu.setImageResource(R.mipmap.jian);
                    Log.e("qqq", holder.fxx.getTranslationX() + "");
//           注意这里的translationX要回到原始位置
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.ly, "translationX", 0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.fxx, "translationX", 0);
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
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.fxx, "translationX", menu_addx - fx);
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
                            holder.fxx.setVisibility(View.GONE);
                            holder.sc.setVisibility(View.GONE);
                        }
                    });
                    isOpen=false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GeRenHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView img;
        private TextView tv_wangming,tv_time,tv_plzuozhe,tv_plcontent;
        private RotateTextView ceshi;
        private TextView tv_content;
        private RelativeLayout shipin_rl;
        private LinearLayout menu_add, ly, fxx, sc;
        private ImageView iv_isopenMenu,zan,fenxiang,xinxi;
        public GeRenHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tv_wangming=itemView.findViewById(R.id.tv_wangming);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_plzuozhe=itemView.findViewById(R.id.tv_plzuozhe);
            tv_plcontent=itemView.findViewById(R.id.tv_plcontent);
            ceshi=itemView.findViewById(R.id.ceshi);
            tv_content=itemView.findViewById(R.id.tv_content);
            menu_add = itemView.findViewById(R.id.menu_add);
            ly = itemView.findViewById(R.id.ly);
            fxx = itemView.findViewById(R.id.fxx);
            sc = itemView.findViewById(R.id.sc);
            iv_isopenMenu = itemView.findViewById(R.id.iv_isopenMenu);
            zan=itemView.findViewById(R.id.zan);
            fenxiang=itemView.findViewById(R.id.fenxiang);
            shipin_rl=itemView.findViewById(R.id.shipin_rl);
        }
    }
}
