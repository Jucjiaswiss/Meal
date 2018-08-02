package com.szu.zhaoyujia.meal.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Adapter.GalleryAdapter;
import com.szu.zhaoyujia.meal.Adapter.RecmFollowAdapter;
import com.szu.zhaoyujia.meal.Adapter.RecmRecmAdapter;
import com.szu.zhaoyujia.meal.MealActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Recommend.SwipeRecmActivity;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 * Created by zhaoyujia on 2017/9/19.
 */

public class RecommendFragment extends Fragment {
    private ImageView head_img;
    private Context context;
    private LinearLayout indexgroup;
    private RecyclerView rv_follow;
    private RecyclerView rv_recm;
    private RelativeLayout rl_gotoRecm;
    private RecmFollowAdapter followAdapter;
    private RecmRecmAdapter recmAdapter;
    private List<String> mDatas_follow;
    private List<String> mDatas_recm;

    public RecommendFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recm, container, false);
        context=getContext();

        /**
         * set the tab logo and the end logo
         */
        TextView txt_header = (TextView)view.findViewById(R.id.top_recm_txt_recm);
        txt_header.setTypeface(Utils.getFont(getContext()));

        getDatas();
        initView(view);
        initIndex();

        return view;
    }

    private void getDatas() {
        String[] a={"http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20130217/8c89a59109d0128a8e7e14.jpg",
                "http://cp1.douguo.net/upload/caiku/6/1/0/600x400_616c014bf82d3b02326f3f8917253950.jpg",
                "http://cp1.douguo.net/upload/caiku/4/9/0/yuan_495091ad11aadeab0567c40010fdd0d0.jpg",
                "http://cp2.douguo.net/upload/caiku/2/0/8/yuan_202838203adf81bafdd82574b281dda8.jpeg",
                "http://cp1.douguo.net/upload/caiku/b/9/7/600x400_b99420d5042355d9826fe9eb3127dcc7.jpg"};
        mDatas_follow = new ArrayList<>(Arrays.asList(a));

        //set the hottest data
        mDatas_recm = new ArrayList<>(Arrays.asList(a));
    }

    private void initIndex() {
        //得到linearlayout的child
        //getIndex;
        // 15 30 30 - 18 40 40
        int index=3;
        TextView child= (TextView) indexgroup.getChildAt(index-1);
        ViewGroup.LayoutParams lp= child.getLayoutParams();
        lp.width=Utils.dip2px(context,40);
        lp.height=Utils.dip2px(context,40);
        child.setLayoutParams(lp);
        child.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
    }

    private void initView(View v) {
        head_img=(ImageView) v.findViewById(R.id.recm_head_img);
        indexgroup= (LinearLayout) v.findViewById(R.id.recm_index_layuot);
        rl_gotoRecm= (RelativeLayout) v.findViewById(R.id.recm_goto_rr);
        rv_follow=(RecyclerView) v.findViewById(R.id.recm_recyclerview_follow);
        rv_recm=(RecyclerView) v.findViewById(R.id.recm_recyclerview_recm);
        LinearLayoutManager lm1= new LinearLayoutManager(getContext());
        LinearLayoutManager lm2= new LinearLayoutManager(getContext());
        //horizontal scroll
        lm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_follow.setLayoutManager(lm1);
        rv_recm.setLayoutManager(lm2);
        followAdapter=new RecmFollowAdapter(context,mDatas_follow);
        recmAdapter=new RecmRecmAdapter(context,mDatas_recm);
        rv_follow.setAdapter(followAdapter);
        rv_recm.setAdapter(recmAdapter);


        Utils.displayResImgviaGlide(context,R.drawable.recm_bg,head_img);

        rl_gotoRecm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), SwipeRecmActivity.class);
                startActivity(intent);
            }
        });

        followAdapter.setOnItemClickListener(new RecmFollowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.setClass(context, MealActivity.class);
                intent.putExtra("mealid",position);
                startActivity(intent);
            }
        });
        recmAdapter.setOnItemClickListener(new RecmRecmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.setClass(context, MealActivity.class);
                intent.putExtra("mealid",position);
                startActivity(intent);
            }
        });
    }


}
