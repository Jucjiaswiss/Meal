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
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;

/**
 * Created by zhaoyujia on 2017/9/25.
 */

public class FollowActicity extends FragmentActivity implements TabLayout.OnTabSelectedListener{
    private ViewPager view_pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_follow);

        setThePager();
    }

    private void setThePager() {
        String[] mTitles = new String[]{"账号","分类"};
        ArrayList<Fragment> list=new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            MeFollowFragment f=new MeFollowFragment();
            Bundle b=new Bundle();
            b.putCharSequence("tag",mTitles[i]);
            f.setArguments(b);
            list.add(f);
        }

        // Initialize the ViewPager and set an adapter
        view_pager = (ViewPager) findViewById(R.id.me_follow_pager);
        view_pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),list,mTitles));
        view_pager.setOffscreenPageLimit(1);

        // Bind the tabs to the ViewPager
        tabs = (TabLayout) findViewById(R.id.me_follow_pager_tabs);
        tabs.setupWithViewPager(view_pager);
        tabs.addOnTabSelectedListener(this);
        tabs.post(new Runnable() {
            @Override
            public void run() {
                Utils.setIndicator(tabs,90,90);//larger the shorter
            }
        });

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
