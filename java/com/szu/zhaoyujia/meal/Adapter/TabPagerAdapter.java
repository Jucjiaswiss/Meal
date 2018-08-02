package com.szu.zhaoyujia.meal.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * Created by zhaoyujia on 2017/9/27.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private ArrayList<Fragment> list ;

    public TabPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] mTitles) {
        super(fm);
        this.list=list;
        this.mTitles=mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
