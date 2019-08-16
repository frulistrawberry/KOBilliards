package com.yuyuka.billiards.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;

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

    }

    @Override
    protected void initView() {

    }
}
