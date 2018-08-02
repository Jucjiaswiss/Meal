package com.szu.zhaoyujia.meal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Tools.Utils;

/**
 * Created by zhaoyujia on 2017/9/20.
 */

public class SearchActivity extends Activity {

    private TextView txt_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    private void initView() {
        txt_search=(TextView) findViewById(R.id.btn_sear_sear);
        txt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.toastShort(getBaseContext(),"search");
            }
        });

    }
}
