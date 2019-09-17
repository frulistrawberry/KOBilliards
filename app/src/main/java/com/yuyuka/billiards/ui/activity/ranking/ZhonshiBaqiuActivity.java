package com.yuyuka.billiards.ui.activity.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.match.RecommendMatchFragment;
import com.yuyuka.billiards.ui.fragment.ranking.DuanweiFragment;
import com.yuyuka.billiards.ui.fragment.ranking.JibaiFragment;
import com.yuyuka.billiards.ui.fragment.ranking.ZhanliFragment;
import com.yuyuka.billiards.ui.fragment.ranking.ZhiyeBangFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ZhonshiBaqiuActivity extends BaseActivity {
    PagerAdapter mAdapter;
    String[] mTitles = {"段位榜","战力榜","击败榜","职业榜"};
    List<Fragment> mFragmentList;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.v_title_divider)
    View vTitleDivider;
    @BindView(R.id.tab_layout)
    MagicIndicator tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int mCurrentPage;
    public static void launcher(Context context){
        context.startActivity(new Intent(context, ZhonshiBaqiuActivity.class));
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_zhonshibaqiuactivity);
        viewPager.setAdapter(mAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,viewPager,mAdapter));
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);

    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new DuanweiFragment());
        mFragmentList.add(new ZhanliFragment());
        mFragmentList.add(new JibaiFragment());
        mFragmentList.add(new ZhiyeBangFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }

}
