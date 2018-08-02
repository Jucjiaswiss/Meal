package com.szu.zhaoyujia.meal.Me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.szu.zhaoyujia.meal.Adapter.MeFollowAdapter;
import com.szu.zhaoyujia.meal.Adapter.MeLikeAdapter;
import com.szu.zhaoyujia.meal.MealActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.SimplePaddingDecoration;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/25.
 */

public class MeFollowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,MeFollowAdapter.OnItemClickListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MeFollowAdapter adapter;
    private ArrayList<String> mData=new ArrayList<>();
    private String tag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_follow, container, false);
        /**
         * 接收tag传过来的tag值进行分类
         */
        tag=getArguments().getCharSequence("tag").toString();
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.me_follow_refresh_layout);
        recyclerView= (RecyclerView) view.findViewById(R.id.me_follow_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getData();

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        adapter=new MeFollowAdapter(getContext(),mData);
        recyclerView.addItemDecoration(new SimplePaddingDecoration(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        return view;
    }

    private void getData() {

        String[] a={"http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20130217/8c89a59109d0128a8e7e14.jpg"};
        mData=new ArrayList<>(Arrays.asList(a));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent();
        intent.setClass(getContext(), MealActivity.class);
        intent.putExtra("mealid",position);
        startActivity(intent);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
        Utils.toastShort(getContext(),"已经是最新数据！");
    }
}
