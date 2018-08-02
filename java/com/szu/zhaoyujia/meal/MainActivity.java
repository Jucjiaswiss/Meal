package com.szu.zhaoyujia.meal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.szu.zhaoyujia.meal.Fragment.DiscoverFragment;
import com.szu.zhaoyujia.meal.Fragment.MineFragment;
import com.szu.zhaoyujia.meal.Fragment.RecommendFragment;
import com.szu.zhaoyujia.meal.Fragment.RecordFragment;
import com.szu.zhaoyujia.meal.Tools.MyRadioButton;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    //buttom tab 控件
    private ViewPager main_viewPager ;
    private RadioGroup main_tab_RadioGroup ;
    private MyRadioButton radio_dis , radio_recm , radio_recd , radio_me ;
    private ArrayList<Fragment> fragmentList ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //界面初始函数，用来获取定义的各控件对应的ID
        InitView();
        //ViewPager初始化函数
        InitViewPager();
    }

    private void InitView() {
        main_tab_RadioGroup = (RadioGroup) findViewById(R.id.main_tab_RadioGroup) ;

        radio_dis = (MyRadioButton) findViewById(R.id.radio_dis) ;
        radio_recm = (MyRadioButton) findViewById(R.id.radio_recm) ;
        radio_recd = (MyRadioButton) findViewById(R.id.radio_recd) ;
        radio_me = (MyRadioButton) findViewById(R.id.radio_me) ;

        main_tab_RadioGroup.setOnCheckedChangeListener(this);
    }

    /**
     * bottom tab setting
     * 2017.9.20
     */
    private void InitViewPager(){
        main_viewPager = (ViewPager) findViewById(R.id.main_ViewPager);

        fragmentList = new ArrayList<Fragment>() ;

        Fragment disFragment = new DiscoverFragment();
        Fragment recmFragment = new RecommendFragment();
        Fragment recdFragment = new RecordFragment();
        Fragment meFragment = new MineFragment();

        //将各Fragment加入数组中
        fragmentList.add(disFragment);
        fragmentList.add(recmFragment);
        fragmentList.add(recdFragment);
        fragmentList.add(meFragment);

        //设置ViewPager的设配器
        main_viewPager.setAdapter(new MyAdapter(getSupportFragmentManager() , fragmentList));
        //当前为第一个页面
        main_viewPager.setCurrentItem(0);
        //ViewPager的页面改变监听器
        main_viewPager.addOnPageChangeListener(new MyListner());
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list ;
        public MyAdapter(FragmentManager fm , ArrayList<Fragment> list)
        {
            super(fm);
            this.list = list ;
        }
        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }

    public class MyListner implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            //获取当前页面用于改变对应RadioButton的状态
            int current = main_viewPager.getCurrentItem();
            switch (current) {
                case 0:
                    main_tab_RadioGroup.check(R.id.radio_dis);
                    break;
                case 1:
                    main_tab_RadioGroup.check(R.id.radio_recm);
                    break;
                case 2:
                    main_tab_RadioGroup.check(R.id.radio_recd);
                    break;
                case 3:
                    main_tab_RadioGroup.check(R.id.radio_me);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int CheckedId) {
            //获取当前被选中的RadioButton的ID，用于改变ViewPager的当前页
            int current=0;
            switch(CheckedId)
            {
                case R.id.radio_dis:
                    current = 0 ;
                    break ;
                case R.id.radio_recm:
                    current = 1 ;
                    break;
                case R.id.radio_recd:
                    current = 2 ;
                    break;
                case R.id.radio_me:
                    current = 3 ;
                    break ;
            }
            if(main_viewPager.getCurrentItem() != current)
            {
                main_viewPager.setCurrentItem(current);
            }
        }
 }

