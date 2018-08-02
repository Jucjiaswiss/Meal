package com.szu.zhaoyujia.meal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Record.AddRecdActivity;
import com.szu.zhaoyujia.meal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class RecdAdapter extends RecyclerView.Adapter<RecdAdapter.ViewHolder> {
    public static final int TYPE_FOOTER = 0;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 1;
    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;
    private View mFooterView;

    public RecdAdapter(Context context, List<String> datas)
    {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    public int getItemViewType(int position) {
        if (mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new ViewHolder(mFooterView);
        }
        ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_recd,
                viewGroup, false));

        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position)
    {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(viewHolder instanceof RecdAdapter.ViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                //rv Data
                viewHolder.mTag.setText(mDatas.get(position));
                //set the content recyclerView
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                viewHolder.mRv.setLayoutManager(layoutManager);

                getData(viewHolder);
                viewHolder.adapter=new RecdContentAdapter(context,viewHolder.mDatas_content);
                viewHolder.mRv.setAdapter(viewHolder.adapter);

                viewHolder.itemView.setTag(position-1);
                return;
            }
            return;
        }else if(getItemViewType(position) == TYPE_FOOTER){
            //footer Data
            viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(context, AddRecdActivity.class);
                    context.startActivity(intent);
                }
            });
            return;
        }else{
            return;
        }
    }

    private void getData(ViewHolder viewHolder) {
        String rv_url="http://pic10.nipic.com/20101104/2457331_113531132546_2.jpg";
        viewHolder.mDatas_content=new ArrayList<>();
        for(int i=0;i<3;i++){
            viewHolder.mDatas_content.add(rv_url);
        }
    }


    @Override
    public int getItemCount()
    {
        if(mFooterView == null){
            return mDatas.size();
        }else
            return mDatas.size() + 1;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTag;
        RecyclerView mRv;
        RecdContentAdapter adapter;
        private List<String> mDatas_content;
        TextView btn_add;


        public ViewHolder(View view)
        {
            super(view);
            if (view == mFooterView){
                //header
                btn_add=(TextView) view.findViewById(R.id.rv_item_recd_rv_footer);
                return;
            }
            mTag= (TextView) view.findViewById(R.id.rv_item_recd_tag);
            mRv= (RecyclerView) view.findViewById(R.id.rv_item_recd_rv);
        }

    }

}
