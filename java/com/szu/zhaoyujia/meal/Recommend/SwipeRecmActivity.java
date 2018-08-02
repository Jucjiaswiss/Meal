package com.szu.zhaoyujia.meal.Recommend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.szu.zhaoyujia.meal.Adapter.RecmAdapter;
import com.szu.zhaoyujia.meal.MealActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/10/9.
 */

public class SwipeRecmActivity extends Activity {

    private SwipeFlingAdapterView flingContainer;
    private List<String> mDatas;
    private RecmAdapter recmAdapter;
    private Context context;
    Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recm_swipe);
        context=this;

        /**
         * set the cardView
         */
        flingContainer = (SwipeFlingAdapterView)findViewById(R.id.swipe_view_recm);
        initSwipeCardView();
    }
    private void initSwipeCardView() {
        initDatas();

        recmAdapter = new RecmAdapter(context,mDatas);

        //set the listener and the adapter
        flingContainer.setAdapter(recmAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                mDatas.remove(0);
                recmAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //add to list
                showTheHeart();
                //add to user list

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Utils.toastShort(context,"不再推荐");
                //Remove from potential list
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                mDatas.add("XML ".concat(String.valueOf(itemsInAdapter)));
                recmAdapter.notifyDataSetChanged();
                itemsInAdapter++;
            }

            @Override
            public void onScroll(float v) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Intent intent=new Intent();
                intent.setClass(SwipeRecmActivity.this, MealActivity.class);
                intent.putExtra("mealid",itemPosition);
                startActivity(intent);
            }
        });
    }

    private void showTheHeart() {
        if(toast != null)
            toast.cancel();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toast_like, null);
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.LEFT,  10, 350);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    private void initDatas() {
        String[] a={"http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20130217/8c89a59109d0128a8e7e14.jpg"};
        mDatas =new ArrayList<>(Arrays.asList(a));
    }

    public void goBack(View v){
        this.finish();
    }
}

