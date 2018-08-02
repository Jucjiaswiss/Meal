package com.szu.zhaoyujia.meal.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Model.Tag;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Tag> mDatas;
    private Context context;
    private int half_window_width;

    public ClassesAdapter(Context context, List<Tag> datas,int width)
    {
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.half_window_width=width/2;
        mDatas = datas;
    }

    /**
     * update data
     */
    public void notifyData(List<Tag> urls){
        this.mDatas=urls;
        this.notifyDataSetChanged();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_classify,
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
        viewHolder.mTxt.setText("#"+mDatas.get(i).getName());
        //set the item of Classify is the half window size
        ViewGroup.LayoutParams lp=viewHolder.mImg.getLayoutParams();
        lp.height=half_window_width;
        lp.width= RecyclerView.LayoutParams.MATCH_PARENT;
        viewHolder.mImg.setLayoutParams(lp);
        viewHolder.mBg.setBackgroundColor(Color.argb(100, 0, 0, 0));

        viewHolder.itemView.setTag(i);
    }


    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        ImageView mImg;
        TextView mTxt;
        TextView mBg;

        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.rv_item_classify_img);
            mTxt= (TextView) view.findViewById(R.id.rv_item_classify_txt);
            mBg= (TextView) view.findViewById(R.id.rv_item_classify_black);
            view.setOnClickListener(this);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction()==MotionEvent.ACTION_DOWN){
                        mBg.setBackgroundColor(Color.TRANSPARENT);
                        mTxt.setTextColor(Color.TRANSPARENT);
                    }else if(event.getAction()==MotionEvent.ACTION_UP
                            ||event.getAction()==MotionEvent.ACTION_MOVE){
                        mBg.setBackgroundColor(Color.argb(100, 0, 0, 0));
                        mTxt.setTextColor(Color.WHITE);
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClick(v,(int)v.getTag());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (itemLongClickListener!=null){
                itemLongClickListener.onItemLongClick((int)v.getTag());
            }
            return false;
        }
    }


    public OnItemClickListener itemClickListener;
    public OnItemLongClickListener itemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener=itemLongClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
}
