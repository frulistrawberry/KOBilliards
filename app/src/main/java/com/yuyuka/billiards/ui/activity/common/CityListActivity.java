package com.yuyuka.billiards.ui.activity.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.nearbyroom.CityListFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CityListActivity extends BaseActivity {


    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    PagerAdapter mAdapter;
    String[] mTitles = {"国内","国际/港澳台"};
    List<Fragment> mFragmentList;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context,CityListActivity.class));
    }

    protected void initView() {
        setContentView(R.layout.activity_city_list);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);

    }



    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new CityListFragment());
        mFragmentList.add(new CityListFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }



}
