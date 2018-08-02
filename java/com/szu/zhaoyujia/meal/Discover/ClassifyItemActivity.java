package com.szu.zhaoyujia.meal.Discover;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.szu.zhaoyujia.meal.Adapter.TabPagerAdapter;
import com.szu.zhaoyujia.meal.Model.Tag;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class ClassifyItemActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener{
    private ViewPager view_pager;
    private TabLayout tabs;
    private String tagId;
    private boolean isFollow=false;
    private TextView btn_follow;
    private TextView tv_title;
    private TextView tv_intro;
    private RelativeLayout rl;
    private LinearLayout down_linearLayout;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        Utils.BmobInitializ(context,this);
        setContentView(R.layout.avtivity_classify_item);


        tagId=getIntent().getStringExtra("tag");
        rl= (RelativeLayout) findViewById(R.id.classi_item_bg);
        down_linearLayout= (LinearLayout) findViewById(R.id.classi_item_downlayout);
        tv_intro=(TextView) findViewById(R.id.classi_item_intro);
        tv_title=(TextView) findViewById(R.id.classi_item_title);
        btn_follow=(TextView) findViewById(R.id.classi_item_btn_follow);

        setTheHeadInfo();
        setThePager();
        /**
         * set the floating animation
         */
        setAnimation();
    }

    private void setAnimation() {

    }

    private void setTheHeadInfo() {
        BmobQuery<Tag> bq=new BmobQuery<>();
        bq.getObject(tagId, new QueryListener<Tag>() {
            @Override
            public void done(Tag tag, BmobException e) {
                //bg
                String url=tag.getImage().getFileUrl();
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(Utils.getWindowSize(context),250) {//设置宽高
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Drawable drawable = new BitmapDrawable(resource);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    rl.setBackground(drawable);//设置背景
                                }
                            }
                        });
                tv_title.setText(tag.getName());
                tv_intro.setText(tag.getIntro());
            }
        });



        //is follow
        setTheBtnFollow();
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFollow)
                    isFollow=false;
                else
                    isFollow=true;

                setTheBtnFollow();
            }
        });
    }

    private void setTheBtnFollow() {
        if(!isFollow){
            btn_follow.setText("+ 关注");
            btn_follow.setTextColor(getResources().getColor(R.color.white));
            btn_follow.setBackground(getDrawable(R.drawable.classi_follow_btn_bg));
        }else{
            btn_follow.setText("已关注");
            btn_follow.setTextColor(getResources().getColor(R.color.btn_gray));
            btn_follow.setBackground(getDrawable(R.drawable.classi_follow_btn_bg_sel));
        }
    }


    private void setThePager() {
        String[] mTitles = new String[]{"最新","最热","最善食"};
        ArrayList<Fragment> list=new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            ClassItemFragment f=new ClassItemFragment();
            Bundle b=new Bundle();
            b.putCharSequence("tag",mTitles[i]);
            f.setArguments(b);
            list.add(f);
        }

        // Initialize the ViewPager and set an adapter
        view_pager = (ViewPager) findViewById(R.id.classi_item_pager);
        view_pager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),list,mTitles));
        view_pager.setOffscreenPageLimit(1);

        // Bind the tabs to the ViewPager
        tabs = (TabLayout) findViewById(R.id.classi_item_pager_tabs);
        tabs.setupWithViewPager(view_pager);
        tabs.addOnTabSelectedListener(this);
        tabs.post(new Runnable() {
            @Override
            public void run() {
                Utils.setIndicator(tabs,40,40);//larger the shorter
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
