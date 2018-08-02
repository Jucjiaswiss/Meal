package com.szu.zhaoyujia.meal.Tools;

import android.app.Application;

/**
 * Created by zhaoyujia on 2017/10/9.
 */

public class MyApplication extends Application {
    private static String BmobID="301d1fc1b1aa048a9652f282e29f1d44";

    public static String getBmobID() {
        return BmobID;
    }

    public static void setBmobID(String bmobID) {
        BmobID = bmobID;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

    }
}
