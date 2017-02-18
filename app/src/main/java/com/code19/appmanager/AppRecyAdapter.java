package com.code19.appmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gh0st on 2017/2/17.
 */

public class AppRecyAdapter extends RecyclerView.Adapter<AppRecyAdapter.ToolsViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<AppModel> mDatas;
    private OnClickListener mOnClickListener;

    public AppRecyAdapter(Context context, List<AppModel> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = list;
    }

    @Override
    public ToolsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToolsViewHolder(mLayoutInflater.inflate(R.layout.item_recy, parent, false));
    }

    @Override
    public void onBindViewHolder(ToolsViewHolder holder, int position) {
        holder.tv_appName.setText(mDatas.get(position).getAppName());
        holder.tv_appPackName.setText(mDatas.get(position).getAppPack());
        holder.iv_appIcon.setImageDrawable(mDatas.get(position).getAppIcon());
        holder.tv_appSize.setText(mDatas.get(position).getAppSize());
        holder.tv_appDate.setText(mDatas.get(position).getAppDate());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ToolsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_appName;
        TextView tv_appPackName;
        ImageView iv_appIcon;
        TextView tv_appSize;
        TextView tv_appDate;

        public ToolsViewHolder(View itemView) {
            super(itemView);
            tv_appName = (TextView) itemView.findViewById(R.id.recy_app_name);
            tv_appPackName = (TextView) itemView.findViewById(R.id.recy_app_packname);
            iv_appIcon = (ImageView) itemView.findViewById(R.id.recy_app_icon);
            tv_appSize = (TextView) itemView.findViewById(R.id.recy_app_size);
            tv_appDate = (TextView) itemView.findViewById(R.id.recy_app_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onItemClick(getAdapterPosition(), v);
                    }
                }
            });
        }
    }

    public void setOnClickListener(OnClickListener onClickListner) {
        this.mOnClickListener = onClickListner;
    }

    public interface OnClickListener {
        void onItemClick(int position, View view);
    }
}
