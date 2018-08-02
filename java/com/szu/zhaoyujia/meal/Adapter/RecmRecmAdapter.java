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

public class RecmRecmAdapter extends RecyclerView.Adapter<RecmRecmAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    public RecmRecmAdapter(Context context, List<String> datats)
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

        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view
                    .findViewById(R.id.rv_item_recm_recm_img);
            mTxt_title=(TextView)view.findViewById(R.id.rv_item_recm_recm_title);
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
        View view = mInflater.inflate(R.layout.rv_item_recm_recm,
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

    public RecmRecmAdapter.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(RecmRecmAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
