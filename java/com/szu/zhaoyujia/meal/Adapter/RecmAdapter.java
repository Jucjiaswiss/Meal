package com.szu.zhaoyujia.meal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class RecmAdapter extends BaseAdapter{
    private List<String> mDatas;
    private Context context;

    public RecmAdapter(Context c, List<String> datas){
        mDatas = datas;
        this.context=c;
    }

    private static class ViewHolder {
        ImageView img;
        TextView txt_title;
        TextView txt_like;
        TextView txt_score;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        if(mDatas==null ||mDatas.size()==0) return null;
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item_recm, parent, false);
            holder  = new ViewHolder();
            convertView.setTag(holder);

            holder.img = (ImageView) convertView.findViewById(R.id.cv_item_recm_img);
            holder.txt_title = (TextView) convertView.findViewById(R.id.cv_item_recm_title);
            holder.txt_like = (TextView) convertView.findViewById(R.id.cv_item_recm_like);
            holder.txt_score = (TextView) convertView.findViewById(R.id.cv_item_recm_score);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //set the Value
        Utils.displayImgviaGlide(context,mDatas.get(position),holder.img);

        return convertView;
    }
}
