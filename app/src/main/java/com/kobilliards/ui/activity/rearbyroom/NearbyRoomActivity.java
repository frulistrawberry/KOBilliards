package com.kobilliards.ui.activity.rearbyroom;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;
import com.kobilliards.event.OffsetChangeEvent;
import com.kobilliards.ui.adapter.NavigatorAdapter;
import com.kobilliards.ui.adapter.PagerAdapter;
import com.kobilliards.ui.fragment.nearbyroom.CollectionRoomFragment;
import com.kobilliards.ui.fragment.nearbyroom.RecommendRoomFragment;
import com.kobilliards.utils.BarUtils;
import com.kobilliards.utils.SizeUtils;
import com.kobilliards.widget.AppBarStateChangeListener;
import com.kobilliards.widget.NoScrollViewPager;
import com.kobilliards.widget.tabindicator.MagicIndicator;
import com.kobilliards.widget.tabindicator.ViewPagerHelper;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NearbyRoomActivity extends BaseActivity {
    @BindView(R.id.v_header)
    View mHeaderView;
    @BindView(R.id.ll_header)
    LinearLayout mHeaderLayout;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.ll_recommend_topic)
    LinearLayout mRecommendTopicLayout;
    @BindView(R.id.ll_search)
    LinearLayout mSearchLayout;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout mCollToolBarLayout;
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
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int i) {
                if (i == 0){
                    mSearchLayout.setVisibility(View.VISIBLE);
                    mRecommendTopicLayout.setVisibility(View.VISIBLE);
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollToolBarLayout.getLayoutParams();
                    //可以滑动，实现折叠悬浮效果
                    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
                    mCollToolBarLayout.getLayoutParams();

                }else {
                    mSearchLayout.setVisibility(View.GONE);
                    mRecommendTopicLayout.setVisibility(View.GONE);
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mCollToolBarLayout.getLayoutParams();

                    //设置不能滑动
                    params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);




                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                EventBus.getDefault().post(new OffsetChangeEvent("NearbyRoomActivity",state));
            }
        });
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new RecommendRoomFragment());
        mFragmentList.add(new CollectionRoomFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }
}
