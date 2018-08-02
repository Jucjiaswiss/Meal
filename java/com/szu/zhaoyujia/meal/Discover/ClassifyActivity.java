package com.szu.zhaoyujia.meal.Discover;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Adapter.ClassesAdapter;
import com.szu.zhaoyujia.meal.Model.Tag;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class ClassifyActivity extends Activity{

    private RecyclerView rv_daily;
    private RecyclerView rv_status;
    private ClassesAdapter adapter_daily;
    private ClassesAdapter adapter_status;
    private List<Tag> mDatas_daily;
    private List<Tag> mDatas_status;
    private int window_width;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        Utils.BmobInitializ(context,this);
        setContentView(R.layout.activity_classify);

        setEndLogo();
        initRecyclerView();
        initData();

    }

    private void setEndLogo() {
        /**
         * set the tab logo and the end logo
         */
        TextView txt_end = (TextView)findViewById(R.id.botm_class_txt_end);
        txt_end.setTypeface(Utils.getFont(this));
    }

    private void initData() {
        BmobQuery<Tag> query = new BmobQuery<Tag>();
        query.addWhereEqualTo("type",1);
        query.findObjects(new FindListener<Tag>() {
            @Override
            public void done(List<Tag> object, BmobException e) {
                if(e==null){
                    for (Tag tag : object) {
                        mDatas_daily.add(tag);
                    }
                    adapter_daily.notifyData(mDatas_daily);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        query = new BmobQuery<Tag>();
        query.addWhereEqualTo("type",2);
        query.findObjects(new FindListener<Tag>() {
            @Override
            public void done(List<Tag> object, BmobException e) {
                if(e==null){
                    //Utils.toastShort(context,"成功："+object.size()+"");
                    for (Tag tag : object) {
                        mDatas_status.add(tag);
                    }
                    adapter_status.notifyData(mDatas_status);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    private void initRecyclerView() {
        mDatas_daily=new ArrayList<>();
        mDatas_status=new ArrayList<>();
        rv_daily=(RecyclerView) findViewById(R.id.classify_recyclerview_daily);
        rv_status=(RecyclerView) findViewById(R.id.classify_recyclerview_status);

        GridLayoutManager lm1 = new GridLayoutManager(this, 2);
        GridLayoutManager lm2 = new GridLayoutManager(this, 2);
        lm1.setOrientation(GridLayoutManager.VERTICAL);
        lm2.setOrientation(GridLayoutManager.VERTICAL);
        rv_daily.setLayoutManager(lm1);
        rv_status.setLayoutManager(lm2);

        window_width=Utils.getWindowSize(this);
        adapter_daily=new ClassesAdapter(this,mDatas_daily,window_width);
        adapter_status=new ClassesAdapter(this,mDatas_status,window_width);
        rv_daily.setAdapter(adapter_daily);
        rv_status.setAdapter(adapter_status);

        adapter_daily.setOnItemClickListener(new ClassesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                click(mDatas_daily,view,position);
            }
        });
        adapter_status.setOnItemClickListener(new ClassesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                click(mDatas_status,view,position);
            }
        });
    }


    public void goBack(View v){
        this.finish();
    }

    private void click(List<Tag> mDatas, View view, int position) {
        Intent intent=new Intent();
        intent.setClass(this, ClassifyItemActivity.class);
        intent.putExtra("tag",mDatas.get(position).getObjectId());
        startActivity(intent);
    }
}
