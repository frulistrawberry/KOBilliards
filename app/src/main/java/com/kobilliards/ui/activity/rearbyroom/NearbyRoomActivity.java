package com.kobilliards.ui.activity.rearbyroom;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;
import com.kobilliards.ui.adapter.PagerAdapter;
import com.kobilliards.ui.fragment.nearbyroom.RecommendRoomFragment;
import com.kobilliards.utils.BarUtils;
import com.kobilliards.utils.SizeUtils;
import com.kobilliards.widget.tabindicator.MagicIndicator;
import com.kobilliards.widget.tabindicator.ViewPagerHelper;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NearbyRoomActivity extends BaseActivity {
    @BindView(R.id.v_header)
    View mHeaderView;
    @BindView(R.id.ll_header)
    LinearLayout mHeaderLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    PagerAdapter mAdapter;
    String[] mTitles = {"推荐球馆","我的收藏"};
    List<Fragment> mFragmentList;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_nearby_room);
        mStatusBar.setVisibility(View.GONE);
        mHeaderView.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(this,88)+ BarUtils.getStatusBarHeight(this)));
        mHeaderLayout.setPadding(SizeUtils.dp2px(this,13),
                SizeUtils.dp2px(this,13)+ BarUtils.getStatusBarHeight(this),
                SizeUtils.dp2px(this,13),SizeUtils.dp2px(this,10));

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.tab_text_normal_color));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.tab_text_select_color));
                simplePagerTitleView.setText(mAdapter.getPageTitle(index));
                simplePagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.tab_indicator_start_color),getResourceColor(R.color.tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
        mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new RecommendRoomFragment());
        mFragmentList.add(new RecommendRoomFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }
}
