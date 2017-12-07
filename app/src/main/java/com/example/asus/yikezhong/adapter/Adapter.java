package com.example.asus.yikezhong.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import com.bumptech.glide.Glide;
import com.example.asus.yikezhong.R;

import java.util.List;

/**
 * Adapter
 * Created by Yancy on 2015/12/4.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> result;
    private final static String TAG = "Adapter";

    public Adapter(Context context, List<String> result) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.griditem, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context)
                .load(result.get(position))
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick.OnClickList(v,position);
                AlertDialog.Builder ab=new AlertDialog.Builder(context);
                ab.setMessage("是否要取消上傳");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //path.remove(p);
                        result.remove(position);
                        notifyDataSetChanged();

                    }
                });
                ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ab.create();
                ab.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }

    }
    public OnClick onClick;
    public interface  OnClick{
        void OnClickList(View v,int p);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
