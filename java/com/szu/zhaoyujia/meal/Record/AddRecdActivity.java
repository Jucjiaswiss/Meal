package com.szu.zhaoyujia.meal.Record;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.util.Util;
import com.jauker.widget.BadgeView;
import com.szu.zhaoyujia.meal.Adapter.TabPagerAdapter;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by zhaoyujia on 2017/10/4.
 */

public class AddRecdActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener,AddRecdFragment.sendDataToActivity{
    private TextView txt_timetag;
    private List<String> options1Items;
    private List<String> options2Items;
    private List<String> chosenfoodIds=new ArrayList<>();;
    private ViewPager view_pager;
    private TabLayout tabs;
    private String[] tags;
    private String[] food;
    private int chooseNum=0;
    private BadgeView badgeView;
    private ImageView iv_check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecd);

        initView();
    }

    @Override
    public void send(String foodid) {
        chosenfoodIds.add(foodid);
        if(chooseNum==0){
            chooseNum++;
            badgeView = new BadgeView(this);
            badgeView.setTargetView(iv_check);
            badgeView.setBackground(10, Color.RED);
            badgeView.setBadgeCount(chooseNum);
        }else{
            chooseNum++;
            badgeView.setBadgeCount(chooseNum);
        }
    }

    private void initView() {
        iv_check=(ImageView) findViewById(R.id.recd_addrecd_check);
        tags =getResources().getStringArray(R.array.meal_tag_category);
        food =getResources().getStringArray(R.array.food_category);
        txt_timetag=(TextView) findViewById(R.id.recd_addrecd_timetag);

        ArrayList<Fragment> list=new ArrayList<>();
        for(int i=0;i<food.length;i++){
            AddRecdFragment f=new AddRecdFragment();
            Bundle b=new Bundle();
            b.putCharSequence("food",food[i]);
            f.setArguments(b);
            list.add(f);
        }

        // Initialize the ViewPager and set an adapter
        view_pager = (ViewPager) findViewById(R.id.recd_addrecd_pager);
        view_pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),list,food));
        view_pager.setOffscreenPageLimit(1);


        // Bind the tabs to the ViewPager
        tabs = (TabLayout) findViewById(R.id.recd_addrecd_pager_tabs);
        tabs.setupWithViewPager(view_pager);
        tabs.addOnTabSelectedListener(this);

        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void TimeTagSelector(View v){
        initSelectorData();
        //条件选择器
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)+"/"
                        + options2Items.get(option2);
                txt_timetag.setText(tx);
            }
        })
                .setTitleText("日期+标签")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.GREEN)//设置分割线的颜色
                .setSelectOptions(5, 0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.GREEN)
                .setSubmitColor(Color.GREEN)
                .setTextColorCenter(Color.BLACK)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        //not 三级联动  所以options1Items里面不是list
        pvOptions.setNPicker(options1Items, options2Items,null);
        pvOptions.show();
    }

    private void initSelectorData() {
        //date(7days-tomorrow)
        options1Items=new ArrayList<>();
        Date date = new Date(); // 新建一个日期
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日"); // 格式化日期
        String beforeDate;
        for (int i=5;i>0;i--){
            beforeDate = sdf.format(Utils.getDateBefore(date,i));
            options1Items.add(beforeDate);
        }
        //add today+tomorrow
        options1Items.add("今天");
        options1Items.add(sdf.format(Utils.getDateAfter(date,1)));

        //tag
        options2Items=new ArrayList<>(Arrays.asList(tags));
    }


    public void goBack(View v){
        this.finish();
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
}
