package com.szu.zhaoyujia.meal.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szu.zhaoyujia.meal.Adapter.MeListAdapter;
import com.szu.zhaoyujia.meal.Me.LikeActicity;
import com.szu.zhaoyujia.meal.Me.FollowActicity;
import com.szu.zhaoyujia.meal.R;
import com.szu.zhaoyujia.meal.Tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Created by zhaoyujia on 2017/9/19.
 */

public class MineFragment extends Fragment {
    private ImageView iv_avatar;
    private TextView txt_name,txt_slogan;
    private ListView me_lv;
    private MeListAdapter adapter;
    private List<String> list;
    private RelativeLayout btn_like,btn_yes;

    public MineFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        iv_avatar= (ImageView) view.findViewById(R.id.me_img_avatar);
        txt_name= (TextView) view.findViewById(R.id.me_txt_name);
        txt_slogan= (TextView) view.findViewById(R.id.me_txt_slogan);
        me_lv=(ListView) view.findViewById(R.id.me_lv);
        btn_like=(RelativeLayout) view.findViewById(R.id.btn_meLike);
        btn_yes=(RelativeLayout) view.findViewById(R.id.btn_meYes);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LikeActicity.class);
                startActivity(intent);
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), FollowActicity.class);
                startActivity(intent);
            }
        });

        setUserInfo();
        setListInfo();

        return view;
    }

    private void setListInfo() {
        String data[]={"我的关注","我的关注","我的关注","我的关注","我的关注","我的关注"};
        list= new ArrayList<>(Arrays.asList(data));
        adapter=new MeListAdapter(getContext(),list);
        me_lv.setAdapter(adapter);
    }

    private void setUserInfo() {
        //avatar
        Utils.displayRoundImgviaGlide(getContext(),
                "http://a4.att.hudong.com/55/63/01200000001541114576325704455.jpg",
                iv_avatar);
        //info

    }

}
