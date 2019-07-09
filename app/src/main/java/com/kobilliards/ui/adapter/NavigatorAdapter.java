package com.kobilliards.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kobilliards.R;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

public class NavigatorAdapter extends CommonNavigatorAdapter {
    private List<Fragment> mFragmentList;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

    public NavigatorAdapter(List<Fragment> mFragmentList, ViewPager mViewPager, PagerAdapter mAdapter) {
        this.mFragmentList = mFragmentList;
        this.mViewPager = mViewPager;
        this.mAdapter = mAdapter;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setNormalColor((R.color.text_color_1));
        simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.text_color_2));
        simplePagerTitleView.setText(mAdapter.getPageTitle(index));
        simplePagerTitleView.setTextSize(18);
        simplePagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        linePagerIndicator.setColors(context.getResources().getColor(R.color.tab_indicator_start_color),context.getResources().getColor(R.color.tab_indicator_end_color));
        return linePagerIndicator;
    }
}
