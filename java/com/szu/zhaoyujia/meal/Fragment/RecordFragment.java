package com.szu.zhaoyujia.meal.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.szu.zhaoyujia.meal.Adapter.RecdAdapter;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by zhaoyujia on 2017/9/19.
 */

public class RecordFragment extends Fragment implements View.OnTouchListener{
    private Context context;
    private ImageView head_img;
    private RelativeLayout iv_time_left;
    private RelativeLayout iv_time_right;
    private TextView txt_time;
    private RecyclerView recyclerView;
    private RecdAdapter adapter;
    private List<String> mDatas_content;
    private MaterialCalendarView calendarView;
    private TextView time_btn;
    private PopupWindow popupwindow;
    private boolean isOpen=false;
    private Date current_date;
    private SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");

    public RecordFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recd, container, false);
        this.context=getContext();

        setTheLogo(view);
        init(view);
        setTheTime();

        return view;
    }

    private void setTheTime() {
        iv_time_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_date=Utils.getDateBefore(current_date,1);
                setTheDate(current_date);
            }
        });
        iv_time_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_date=Utils.getDateAfter(current_date,1);
                setTheDate(current_date);
            }
        });
        txt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen){
                    initmPopupWindowView();
                    popupwindow.showAsDropDown(v, 0, 5);
                    isOpen=true;
                }else{
                    popupwindow.dismiss();
                    popupwindow = null;
                    isOpen=false;
                }
            }
        });
    }

    private void initmPopupWindowView() {
        // // 获取自定义布局文件pop.xml的视图
        final View customView = getActivity().getLayoutInflater().inflate(R.layout.view_time,
                null, false);
        calendarView= (MaterialCalendarView) customView.findViewById(R.id.recd_calendarView);
        time_btn= (TextView) customView.findViewById(R.id.recd_calendarView_btn);
        current_date=new Date();
        calendarView.setCurrentDate(current_date);
        calendarView.setDateSelected(current_date,true);
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //setthe text
                current_date=date.getDate();
            }
        });


        time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheDate(current_date);
                //dismiss
                popupwindow.dismiss();
                popupwindow = null;
                isOpen=false;
            }
        });

        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow.setAnimationStyle(R.style.AnimationFade);
        popupwindow.setOutsideTouchable(true);
    }

    private void setTheDate(Date date) {
        if(sdf.format(date).equals(sdf.format(new Date()))){
            txt_time.setText("今天");
        }else
            txt_time.setText(sdf.format(date));
    }

    private void setTheLogo(View view) {
        /**
         * set the tab logo and the end logo
         */
        TextView txt_header = (TextView)view.findViewById(R.id.top_recd_txt_recd);
        txt_header.setTypeface(Utils.getFont(getContext()));
    }

    private void init(View view) {
        head_img=(ImageView) view.findViewById(R.id.recd_head_img);
        Utils.displayResImgviaGlide(context,R.drawable.recd_bg,head_img);

        txt_time= (TextView) view.findViewById(R.id.recd_time);
        iv_time_left= (RelativeLayout) view.findViewById(R.id.recd_time_left);
        iv_time_right= (RelativeLayout) view.findViewById(R.id.recd_time_right);

        //init Date
        current_date=new Date();
        setTheDate(current_date);

        recyclerView=(RecyclerView) view.findViewById(R.id.recd_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        getData();
        adapter=new RecdAdapter(context,mDatas_content);
        recyclerView.setAdapter(adapter);

        setFooterView(recyclerView);
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(context).inflate(R.layout.rv_item_recd_footer, recyclerView, false);
        adapter.setFooterView(footer);
    }

    private void getData() {
        String words="早餐";
        mDatas_content=new ArrayList<>();
        for(int i=0;i<3;i++){
            mDatas_content.add(words);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (popupwindow != null && popupwindow.isShowing()) {
            popupwindow.dismiss();
            popupwindow = null;
            isOpen=false;
        }
        return false;
    }
}
