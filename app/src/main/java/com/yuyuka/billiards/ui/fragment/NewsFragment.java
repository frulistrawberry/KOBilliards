package com.yuyuka.billiards.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.news.NewsListFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    MagicIndicator mIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    List<Fragment> mFragmentList;
    PagerAdapter mAdapter;
    String[] mTitles = {"关注","推荐","文章","视频","小视频"};
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_news,parent,false);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsListFragment.newFragment(4));
        mFragmentList.add(NewsListFragment.newFragment(3));
        mFragmentList.add(NewsListFragment.newFragment(0));
        mFragmentList.add(NewsListFragment.newFragment(2));
        mFragmentList.add(NewsListFragment.newFragment(1));
        mAdapter = new PagerAdapter(getChildFragmentManager(),mFragmentList,mTitles);
    }

    @Override
    protected void initView() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mIndicator.setNavigator(commonNavigator);
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(mIndicator, mViewPager);
    }
}
