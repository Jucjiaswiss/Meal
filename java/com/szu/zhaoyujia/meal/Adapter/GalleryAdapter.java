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

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;
    private int type;

    public GalleryAdapter(Context context, List<String> datats,int type)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
        this.context=context;
    }

    /**
     * update data
     */
    public void notifyData(ArrayList<String> urls){
        this.mDatas=urls;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView mImg;
        TextView mTxt_title;
        TextView mTxt_like;
        TextView mTxt_score;

        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view
                    .findViewById(R.id.rv_item_dis_hottest_img);
            mTxt_title=(TextView)view.findViewById(R.id.rv_item_dis_hottest_title);
            mTxt_like=(TextView)view.findViewById(R.id.rv_item_dis_like);
            mTxt_score=(TextView)view.findViewById(R.id.rv_item_dis_score);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClick(v,(int)v.getTag());
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.rv_item_dis_hottest,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

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

    public GalleryAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(GalleryAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
