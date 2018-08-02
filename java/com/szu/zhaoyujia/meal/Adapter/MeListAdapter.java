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

public class MeListAdapter extends BaseAdapter{
    private List<String> mDatas;
    private Context context;

    public MeListAdapter(Context c, List<String> datas){
        mDatas = datas;
        this.context=c;
    }

    private static class ViewHolder {
        TextView textView;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_me, parent, false);
            holder  = new ViewHolder();
            convertView.setTag(holder);

            holder.textView= (TextView) convertView.findViewById(R.id.lv_item_me_txt);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(mDatas.get(position));

        return convertView;
    }
}
