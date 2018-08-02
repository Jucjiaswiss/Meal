package com.szu.zhaoyujia.meal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyujia on 2017/9/27.
 */

public class MealActivity extends Activity {
    private ImageView iv_img;
    private ImageView btn_like;
    private TextView txt_head_title;
    private TextView txt_title;
    private TextView txt_intro;
    private TextView txt_tag;
    private TextView txt_like;
    private TextView txt_score;
    private RecyclerView rv_ingredient;
    private MealIngredientAdapter adapter;
    private List<String> mDatas_ingredient;
    private boolean isLike=false;
    private String img_url;
    private String mealId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        //meal id
        mealId = getIntent().getExtras().getString("mealid");

        btn_like= (ImageView) findViewById(R.id.meal_btn_like);
        txt_head_title=(TextView) findViewById(R.id.top_meal_title);
        rv_ingredient= (RecyclerView) findViewById(R.id.meal_ingredient_rv);

        initInfo();
        setAdapter();

        setLikeBtn();
        //is like
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLike)
                    isLike=false;
                else
                    isLike=true;
                setLikeBtn();
            }
        });
    }

    private void setLikeBtn() {
        if(!isLike){
            Utils.displayResImgviaGlide(this,R.drawable.btn_like,btn_like);
        }else{
            Utils.displayResImgviaGlide(this,R.drawable.btn_like_sel,btn_like);
        }
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_ingredient.setLayoutManager(layoutManager);
        adapter=new MealIngredientAdapter(this,mDatas_ingredient);
        rv_ingredient.setAdapter(adapter);
        setHeaderView(rv_ingredient);
    }

    private void setHeaderView(RecyclerView rv) {
        View header = LayoutInflater.from(this).inflate(R.layout.activity_meal_header, rv, false);
        adapter.setHeaderView(header);
    }

    private void initInfo() {
        img_url="http://pic15.nipic.com/20110727/7683136_123102431146_2.jpg";

        String rv_url="http://pic10.nipic.com/20101104/2457331_113531132546_2.jpg";
        mDatas_ingredient=new ArrayList<>();
        for(int i=0;i<9;i++){
            mDatas_ingredient.add(rv_url);
        }
    }

    private void setHeaderInfo(){
        Utils.displayImgviaGlide(this,img_url,iv_img);
    }

    /**
     * adapter
     */
    public class MealIngredientAdapter extends RecyclerView.Adapter<MealIngredientAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<String> mDatas;
        private Context context;
        public static final int TYPE_HEADER = 0;  //说明是带有Header的
        public static final int TYPE_NORMAL = 1;
        private View mHeaderView;

        public MealIngredientAdapter(Context context, List<String> datas) {
            this.context=context;
            mInflater = LayoutInflater.from(context);
            mDatas = datas;
        }

        public View getmHeaderView() {
            return mHeaderView;
        }

        public void setHeaderView(View header) {
            mHeaderView = header;
            notifyItemInserted(0);
        }

        /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
        @Override
        public int getItemViewType(int position) {
            if (mHeaderView == null){
                return TYPE_NORMAL;
            }
            if (position == 0){
                //第一个item应该加载Header
                return TYPE_HEADER;
            }
            return TYPE_NORMAL;
        }

        public void notifyData(ArrayList<String> urls){
            this.mDatas=urls;
            this.notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            if(mHeaderView != null && viewType == TYPE_HEADER) {
                return new ViewHolder(mHeaderView);
            }

            ViewHolder viewHolder = new ViewHolder(mInflater.inflate(R.layout.rv_item_meal_ingredient,
                    viewGroup, false));
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if(getItemViewType(position) == TYPE_NORMAL){
                if(holder instanceof ViewHolder) {
                    //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                    //rv Data
                    //holder.mName.setText(mDatas.get(position-1));
                    Utils.displayImgviaGlide(context,mDatas.get(position-1),holder.mImg);
                    return;
                }
                return;
            }else if(getItemViewType(position) == TYPE_HEADER){
                //header Data
                setHeaderInfo();
                return;
            }else{
                return;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImg;
            TextView mName;

            public ViewHolder(View view) {
                super(view);
                if (view == mHeaderView){
                    //header
                    iv_img=(ImageView) view.findViewById(R.id.meal_img);
                    txt_title=(TextView) view.findViewById(R.id.meal_title);
                    txt_intro=(TextView) view.findViewById(R.id.meal_intro);
                    txt_tag=(TextView) view.findViewById(R.id.meal_tag);
                    txt_like=(TextView) view.findViewById(R.id.meal_like);
                    txt_score=(TextView) view.findViewById(R.id.meal_score);
                    return;
                }
                mImg = (ImageView) view.findViewById(R.id.rv_item_meal_img);
                mName= (TextView) view.findViewById(R.id.rv_item_meal_name);
            }
        }

        @Override
        public int getItemCount() {
            if(mHeaderView == null ){
                return mDatas.size();
            }else
                return mDatas.size() + 1;
        }
    }

    public void setShareBtn(View v) {

    }

    public void goBack(View v){
        this.finish();
    }

}
