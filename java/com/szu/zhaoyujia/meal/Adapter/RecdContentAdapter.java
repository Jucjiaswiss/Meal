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

public class RecdContentAdapter extends RecyclerView.Adapter<RecdContentAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    public RecdContentAdapter(Context context, List<String> datas)
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
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_recd_rv,
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
        viewHolder.mName.setText("土豆");

        viewHolder.itemView.setTag(i);
    }


    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        ImageView mImg;

        public ViewHolder(View view)
        {
            super(view);
            mImg= (ImageView) view.findViewById(R.id.rv_item_recd_rv_img);
            mName= (TextView) view.findViewById(R.id.rv_item_recd_rv_name);
        }

    }

}
