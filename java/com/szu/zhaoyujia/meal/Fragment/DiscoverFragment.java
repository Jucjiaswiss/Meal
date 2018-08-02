package com.szu.zhaoyujia.meal.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Adapter.GalleryAdapter;
import com.szu.zhaoyujia.meal.Discover.ClassifyActivity;
import com.szu.zhaoyujia.meal.MealActivity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.SearchActivity;
import com.szu.zhaoyujia.meal.Tools.GlideImageLoader;
import com.szu.zhaoyujia.meal.Tools.Utils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Created by zhaoyujia on 2017/9/19.
 */

public class DiscoverFragment extends Fragment{
    private Banner banner;
    private RecyclerView rv_latest;
    private RecyclerView rv_hottest;
    private RecyclerView rv_healthiest;
    private GalleryAdapter rv_adapter_latest;
    private GalleryAdapter rv_adapter_hottest;
    private GalleryAdapter rv_adapter_healthiest;
    private List<String> mDatas_latest;
    private List<String> mDatas_hottest;
    private List<String> mDatas_healthiest;
    private List<String> mDatas_banner;


    public DiscoverFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dis, container, false);

        /**
         * set the tab logo and the end logo
         */
        TextView txt_header = (TextView)view.findViewById(R.id.top_dis_txt_dis);
        TextView txt_end = (TextView)view.findViewById(R.id.botm_dis_txt_end);
        txt_header.setTypeface(Utils.getFont(getContext()));
        txt_end.setTypeface(Utils.getFont(getContext()));

        /**
         * set the classify and search btn
         */
        TextView btn_classify=(TextView)view.findViewById(R.id.top_dis_btn_classify);
        btn_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClassifyActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_sear= (ImageButton) view.findViewById(R.id.top_dis_btn_search);
        btn_sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        /**
         * set the banner
         */
        banner = (Banner) view.findViewById(R.id.dis_banner);
        setBanner();

        /**
         * set the recyclerView
         */
        //update the data
        initLatestData();
        initRecyclerView(view);

        //notifyData(mDatas_latest,mDatas_hottest,mDatas_healthiest);

        return view;
    }

    private void notifyData(List<String> l1, List<String> l2, List<String> l3) {
        rv_adapter_latest.notifyData((ArrayList<String>) l1);
        rv_adapter_hottest.notifyData((ArrayList<String>) l2);
        rv_adapter_healthiest.notifyData((ArrayList<String>) l3);
    }

    private void initRecyclerView(View view) {
        rv_latest = (RecyclerView) view.findViewById(R.id.dis_recyclerview_latest);
        rv_hottest = (RecyclerView) view.findViewById(R.id.dis_recyclerview_hottest);
        rv_healthiest= (RecyclerView) view.findViewById(R.id.dis_recyclerview_healthiest);
        LinearLayoutManager lm1= new LinearLayoutManager(getContext());
        LinearLayoutManager lm2= new LinearLayoutManager(getContext());
        LinearLayoutManager lm3= new LinearLayoutManager(getContext());
        //horizontal scroll
        lm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_latest.setLayoutManager(lm1);
        rv_hottest.setLayoutManager(lm2);
        rv_healthiest.setLayoutManager(lm3);

        //connect data -adapt
        rv_adapter_latest = new GalleryAdapter(getContext(), mDatas_latest,1);
        rv_adapter_hottest = new GalleryAdapter(getContext(), mDatas_hottest,2);
        rv_adapter_healthiest = new GalleryAdapter(getContext(), mDatas_healthiest,3);
        rv_latest.setAdapter(rv_adapter_latest);
        rv_hottest.setAdapter(rv_adapter_hottest);
        rv_healthiest.setAdapter(rv_adapter_healthiest);

        //onItemClisk
        rv_adapter_latest.setOnItemClickListener(listener);
        rv_adapter_hottest.setOnItemClickListener(listener);
        rv_adapter_healthiest.setOnItemClickListener(listener);
    }

    GalleryAdapter.OnItemClickListener listener=new GalleryAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent=new Intent();
            intent.setClass(getContext(), MealActivity.class);
            intent.putExtra("mealid",position);
            startActivity(intent);
        }
    };

    private void initLatestData() {
        //set the latest data
        String[] a={"http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                "http://a1.att.hudong.com/77/04/19300001355189132244041329283_140.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20130217/8c89a59109d0128a8e7e14.jpg",
        "http://cp1.douguo.net/upload/caiku/6/1/0/600x400_616c014bf82d3b02326f3f8917253950.jpg",
        "http://cp1.douguo.net/upload/caiku/4/9/0/yuan_495091ad11aadeab0567c40010fdd0d0.jpg",
        "http://cp2.douguo.net/upload/caiku/2/0/8/yuan_202838203adf81bafdd82574b281dda8.jpeg",
        "http://cp1.douguo.net/upload/caiku/b/9/7/600x400_b99420d5042355d9826fe9eb3127dcc7.jpg"};


        mDatas_latest = new ArrayList<>(Arrays.asList(a));
        //set the hottest data
        mDatas_hottest = new ArrayList<>(Arrays.asList(a));
        //set the healthiest data
        mDatas_healthiest = new ArrayList<>(Arrays.asList(a));
    }

    private void setBanner() {
        //setImages
        String[] images= new String[] {"http://img.article.pchome.net/00/26/70/41/pic_lib/wm/psd_31.jpg",
                "http://pic18.nipic.com/20111219/8656301_115037215155_2.jpg",
                "http://www.visitsz.com:801/uploadfiles/images/%E9%A9%AC%E6%9D%A5%E7%BE%8E%E9%A3%9F.jpg",
                "http://images.china.cn/attachement/jpg/site1000/20110209/001ec94a24510ebc3a120b.jpg"
        };
        List<String> list_images= Arrays.asList(images);

        banner.setImages(list_images);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        //onclickListener
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //下标从0开始:和Image数组进行比较，得到点击的编号
                Intent intent=new Intent();
                intent.setClass(getContext(), MealActivity.class);
                intent.putExtra("mealid",position);
                startActivity(intent);
            }
        });
        //still the default one
        //banner.setBannerAnimation(Transformer.ZoomOutSlide);

        banner.start();
    }

//    /**
//     * 下拉刷新
//     */
//    @Override
//    public void onRefresh() {
//        swipeRefreshLayout.setRefreshing(true);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        }, 2000);
//        Utils.toastShort(getContext(),"已经是最新数据！");
//    }
}
