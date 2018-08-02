package com.szu.zhaoyujia.meal.Adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Model.Food;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class AddRecdItemAdapter extends RecyclerView.Adapter<AddRecdItemAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Food> mDatas;
    private Context context;

    public AddRecdItemAdapter(Context context, List<Food> datas)
    {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    /**
     * update data
     */
    public void notifyData(ArrayList<Food> datas){
        this.mDatas=datas;
        this.notifyDataSetChanged();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_addrecd,
                viewGroup, false));
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        Utils.displayImgviaGlide(context,mDatas.get(i).getImage().getFileUrl(),viewHolder.mImg);
        viewHolder.mName.setText(mDatas.get(i).getName());

        //set the index color
        GradientDrawable myGrad = (GradientDrawable)viewHolder.mIndex.getDrawable();
        int index=mDatas.get(i).getIndex();
        int color;
        switch (index){
            case 1:color=R.color.red;break;
            case 2:color=R.color.yellow;break;
            case 3:color=R.color.btn_green;break;
            default: color=R.color.btn_green;break;
        }
        myGrad.setColor(ContextCompat.getColor(context, color));

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
        TextView mName;
        ImageView mIndex;

        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.rv_item_addrecd_img);
            mName= (TextView) view.findViewById(R.id.rv_item_addrecd_name);
            mIndex= (ImageView) view.findViewById(R.id.rv_item_addrecd_index);
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
