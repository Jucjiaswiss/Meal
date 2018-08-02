package com.szu.zhaoyujia.meal.Record;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.szu.zhaoyujia.meal.Adapter.AddRecdItemAdapter;
import com.szu.zhaoyujia.meal.Model.Food;
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
 * Created by zhaoyujia on 2017/10/5.
 */

public class AddRecdFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AddRecdItemAdapter.OnItemClickListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AddRecdItemAdapter adapter;
    private ArrayList<Food> mDatas=new ArrayList<>();
    private String food;
    private Context context;
    public sendDataToActivity mySendDataToActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recd_addrecd, container, false);
        /**
         * 接收tag传过来的tag值进行分类
         */
        context=getContext();
        Utils.BmobInitializ(context,getActivity());
        food=getArguments().getCharSequence("food").toString();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.recd_addrecd_refresh_layout);
        recyclerView= (RecyclerView) view.findViewById(R.id.recd_addrecd_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mDatas=new ArrayList<>();


        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.btn_green);
        adapter=new AddRecdItemAdapter(getContext(),mDatas);
        recyclerView.setAdapter(adapter);
        getData();
        adapter.setOnItemClickListener(this);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void getData() {
        final long startTime=System.currentTimeMillis();
        BmobQuery<Food> query = new BmobQuery<Food>();
        query.addWhereEqualTo("type",food);
        query.findObjects(new FindListener<Food>() {
            @Override
            public void done(List<Food> object, BmobException e) {
                Long time=(System.currentTimeMillis() - startTime);
                Utils.toastLong(context,"间隔时间："+time+"ms");

                if(e==null){
                    for (Food food : object) {
                        mDatas.add(food);
                    }
                    adapter.notifyData(mDatas);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    /*
     * fragment生命周期中，onAttach在与activity关联后，首先执行， 并且，在这里会返回一个activity对象，
     * 获得activity后，即可用于后面调用activity中方法
     */
    @Override
    public void onAttach(Activity activity) {
        mySendDataToActivity = (sendDataToActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public void onItemClick(View view, final int position) {
        // 调用activity中实现的接口方法
        mySendDataToActivity.send(mDatas.get(position).getObjectId());
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                adapter.notifyData(mDatas);
                getData();
                adapter.notifyData(mDatas);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


    // 定义一个内部接口，接口中方法用于处理发送给activity的数据
    public interface sendDataToActivity {
        public void send(String foodid);
    }
}
