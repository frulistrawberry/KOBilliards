package com.yuyuka.billiards.ui.activity.ko;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.ko.KOClassFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KOClassActivity extends BaseActivity {

    PagerAdapter mAdapter;
    String[] mTitles = {"单人模式","KO学堂"};
    List<Fragment> mFragmentList;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("KO学堂").showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_ko_class);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter,R.color.text_color_11,R.color.ko_start_color,R.color.ko_end_color));
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(KOClassFragment.newFragment(0));
        mFragmentList.add(KOClassFragment.newFragment(1));
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }
}
