package com.szu.zhaoyujia.meal.Discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.szu.zhaoyujia.meal.Adapter.ClassesAdapter;
import com.szu.zhaoyujia.meal.Adapter.ClassiItemAdapter;
import com.szu.zhaoyujia.meal.MealActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.SimplePaddingDecoration;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhaoyujia on 2017/9/27.
 */

public class ClassItemFragment extends Fragment implements View.OnTouchListener,ClassiItemAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private ClassiItemAdapter adapter;
    private ArrayList<String> mDatas=new ArrayList<>();
    private String tag;
    GestureDetector myGestureDetector= new GestureDetector(new myGestureListener());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dis_classi_item, container, false);

        recyclerView= (RecyclerView) view.findViewById(R.id.classi_dis_item_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getData();

        adapter=new ClassiItemAdapter(getContext(),mDatas);
        recyclerView.addItemDecoration(new SimplePaddingDecoration(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        setGestureListener();

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent();
        intent.setClass(getContext(), MealActivity.class);
        intent.putExtra("mealid",position);
        startActivity(intent);
    }

    private void setGestureListener() {
        //recyclerView.setOnTouchListener(this);
        //recyclerView.setOnTouchListener(null);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        myGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    class myGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1!=null&&e2!=null){
                if (e1.getY()-e2.getY()>50)
                    Utils.toastShort(getContext(),"向上滑动");
                recyclerView.setOnTouchListener(null);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


    private void getData() {

        String[] a={"http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20130217/8c89a59109d0128a8e7e14.jpg"};
        mDatas=new ArrayList<>(Arrays.asList(a));
    }

}
