package com.yuyuka.billiards.ui.activity.match;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.match.RecommendMatchFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NearbyMatchActivity extends BaseActivity {
    PagerAdapter mAdapter;
    String[] mTitles = {"推荐比赛","我的收藏"};
    List<Fragment> mFragmentList;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;

    public static void launcher(Context context){
        context.startActivity(new Intent(context, NearbyMatchActivity.class));
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_nearby_match);
        mStatusBar.setVisibility(View.GONE);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new RecommendMatchFragment());
        mFragmentList.add(new RecommendMatchFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }
}
