package com.example.asus.yikezhong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.yikezhong.R;

/**
 * Created by asus on 2017/11/30.
 */

public class DuanZi_AAdapter extends RecyclerView.Adapter<DuanZi_AAdapter.ViewHolder1>{
    private Context context;
    private String[] s;
    public DuanZi_AAdapter(Context context, String[] s) {
        this.context = context;
        this.s = s;
    }

    @Override
    public DuanZi_AAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.rycle_item,null);
        ViewHolder1 viewHolder1=new ViewHolder1(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(DuanZi_AAdapter.ViewHolder1 holder, int position) {
        if(s.length==1)
        {   holder.img_duanzi_3.setVisibility(View.VISIBLE);
            Glide.with(context).load(s[position]).into(holder.img_duanzi_3);
        }
        else if(s.length==2)
        {holder.img_duanzi_2.setVisibility(View.VISIBLE);
            Glide.with(context).load(s[position]).into(holder.img_duanzi_2);
        }
        else
        {holder.img_duanzi_1.setVisibility(View.VISIBLE);
            Glide.with(context).load(s[position]).into(holder.img_duanzi_1);
        }


    }

    @Override
    public int getItemCount() {
        return s.length;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView img_duanzi_1,img_duanzi_2,img_duanzi_3;

        public ViewHolder1(View itemView) {
            super(itemView);
            img_duanzi_1 = itemView.findViewById(R.id.img_duanzi_1);
            img_duanzi_2 = itemView.findViewById(R.id.img_duanzi_2);
            img_duanzi_3 = itemView.findViewById(R.id.img_duanzi_3);

        }
    }

 }
