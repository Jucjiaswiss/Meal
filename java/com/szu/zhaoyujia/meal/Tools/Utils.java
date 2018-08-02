package com.szu.zhaoyujia.meal.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class Utils {

    /**
     * Bmob Initiation
     */
    public static void BmobInitializ(Context context,Activity activity) {
        String bmobId= ((MyApplication) (activity.getApplication())).getBmobID();
        Bmob.initialize(context,bmobId);
    }
    /**
     * Bmob SMS Initiation
     */
    public static void BmobSMSInitializ(Context context,Activity activity) {
        String bmobId= ((MyApplication) (activity.getApplication())).getBmobID();
        BmobSMS.initialize(context,bmobId);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale+0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 得到几天前的时间
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int getWindowSize(Context context) {
        int window_width;
        DisplayMetrics outMetrics = context.getResources().getDisplayMetrics();
        window_width = outMetrics.widthPixels;
        return window_width;
    }
    public static int getWindowHeight(Context context) {
        int window_height;
        DisplayMetrics outMetrics = context.getResources().getDisplayMetrics();
        window_height = outMetrics.heightPixels;
        return window_height;
    }

    public static void toastShort(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context c, String s) {
        Toast.makeText(c, s, Toast.LENGTH_LONG).show();
    }

    //load images from internet
    public static void displayImgviaGlide(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    //load images from resource
    public static void displayResImgviaGlide(Context context, int path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }

    //load images from internet- round image
    public static void displayRoundImgviaGlide(final Context context, String path, final ImageView imageView) {
        Glide.with(context).load(path).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static Typeface getFont(Context context) {
        String path = "fonts" + File.separator + "brushfont.ttf";
        Typeface font = Typeface.createFromAsset(context.getAssets(), path);
        return font;
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
