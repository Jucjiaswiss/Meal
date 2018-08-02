package com.szu.zhaoyujia.meal.Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.szu.zhaoyujia.meal.Adapter.TabPagerAdapter;
import com.szu.zhaoyujia.meal.R;

import java.util.ArrayList;

/**
 * Created by zhaoyujia on 2017/9/25.
 */

public class LikeActicity extends FragmentActivity implements TabLayout.OnTabSelectedListener{
    private ViewPager view_pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_like);

        setThePager();
    }

    private void setThePager() {
        String[] mTitles = new String[]{"全部","早餐", "午餐", "晚餐", "甜品","饮料","零食"};
        ArrayList<Fragment> list=new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            MeLikeFragment f=new MeLikeFragment();
            Bundle b=new Bundle();
            b.putCharSequence("tag",mTitles[i]);
            f.setArguments(b);
            list.add(f);
        }

        // Initialize the ViewPager and set an adapter
        view_pager = (ViewPager) findViewById(R.id.me_like_pager);
        view_pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),list,mTitles));
        view_pager.setOffscreenPageLimit(1);


        // Bind the tabs to the ViewPager
        tabs = (TabLayout) findViewById(R.id.me_like_pager_tabs);
        tabs.setupWithViewPager(view_pager);
        tabs.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int current=tab.getPosition();
        if(view_pager.getCurrentItem() != current)
        {
            view_pager.setCurrentItem(current);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void goBack(View v){
        this.finish();
    }
}
