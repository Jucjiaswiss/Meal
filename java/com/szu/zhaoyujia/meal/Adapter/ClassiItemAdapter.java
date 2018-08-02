package com.szu.zhaoyujia.meal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class ClassiItemAdapter extends RecyclerView.Adapter<ClassiItemAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    public ClassiItemAdapter(Context context, List<String> datas)
    {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    /**
     * update data
     */
    public void notifyData(ArrayList<String> urls){
        this.mDatas=urls;
        this.notifyDataSetChanged();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_classi_item,
                viewGroup, false));
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        Utils.displayImgviaGlide(context,mDatas.get(i),viewHolder.mImg);

        viewHolder.itemView.setTag(i);
    }


    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView mImg;
        TextView mTitle;
        TextView mIntro;

        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.rv_item_classi_item_img);
            mTitle= (TextView) view.findViewById(R.id.rv_item_classi_item_title);
            mIntro= (TextView) view.findViewById(R.id.rv_item_classi_item_intro);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClick(v,(int)v.getTag());
            }
        }

    }


    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
