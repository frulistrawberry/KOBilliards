package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;
import com.kobilliards.ui.adapter.PagerAdapter;
import com.kobilliards.ui.fragment.nearbyroom.BilliardsRoomCoachFragment;
import com.kobilliards.utils.ScreenUtils;
import com.kobilliards.utils.log.LogUtil;
import com.kobilliards.widget.ObservableNestedScrollView;
import com.kobilliards.widget.tabindicator.MagicIndicator;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BilliardsRoomDetailActivity extends BaseActivity {


    @BindView(R.id.images)
    FrameLayout images;
    private String[] mTitle = {"球台预定", "球房活动", "球友点评", "设施亮点"};
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.scroll_view)
    ObservableNestedScrollView mScrollView;
    @BindView(R.id.ll_reserve)
    LinearLayout mReserveLayout;
    @BindView(R.id.ll_active)
    LinearLayout mActiveLayout;
    @BindView(R.id.ll_comment)
    LinearLayout mCommentLayut;
    @BindView(R.id.ll_facilities)
    LinearLayout mFacilitiesLayout;
    @BindView(R.id.ll_star)
    LinearLayout mStarLayout;
    @BindView(R.id.ll_room_desc)
    LinearLayout mllroom;

    public static void launcher(Context context) {
        Intent intent = new Intent(context, BilliardsRoomDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房详情").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_detail);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.text_color_1));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.text_color_2));
                simplePagerTitleView.setText(mTitle[index]);
                simplePagerTitleView.setOnClickListener(v -> {
                    int reserveTop = mReserveLayout.getTop();
                    int activeTop = mActiveLayout.getTop();
                    int facilitiesTop = mFacilitiesLayout.getTop() - 100;
                    int commentTop = mCommentLayut.getTop();
                    switch (index) {
                        case 0:
                            mScrollView.scrollTo(0, reserveTop);
                            break;
                        case 1:
                            mScrollView.scrollTo(0, activeTop);
                            break;
                        case 2:
                            mScrollView.scrollTo(0, commentTop);
                            break;
                        case 3:
                            mScrollView.scrollTo(0, facilitiesTop);
                            break;
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.tab_indicator_start_color), getResourceColor(R.color.tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mTabLayout.setNavigator(commonNavigator);
        List<Fragment> coachList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            coachList.add(new BilliardsRoomCoachFragment());
        }
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(this)));
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), coachList));
        mScrollView.setScrollViewListener((scrollView, x, y, oldx, oldy) -> {
            int reserveTop = mReserveLayout.getTop();
            int reserveBottom = mReserveLayout.getBottom();
            int activeTop = mActiveLayout.getTop();
            int activeBottom = mStarLayout.getBottom();
            int facilitiesTop = mFacilitiesLayout.getTop() - 100;
            int facilitiesBottom = mFacilitiesLayout.getBottom() - 100;
            int commentBottom = mCommentLayut.getBottom() - 100;
            int commentTop = mCommentLayut.getTop();

            if (y >= reserveTop && y <= reserveBottom) {
                mTabLayout.onPageScrolled(0, Float.valueOf(y - reserveTop) / reserveBottom, 0);
            }
            if (y >= activeTop && y <= activeBottom) {
                mTabLayout.onPageScrolled(1, Float.valueOf(y - activeTop) / activeBottom, 0);
            }

            if (y >= commentTop && y <= commentBottom) {
                mTabLayout.onPageScrolled(2, Float.valueOf(y - commentTop) / commentBottom, 0);
            }

            if (y >= facilitiesTop && y <= facilitiesBottom) {
                mTabLayout.onPageScrolled(3, Float.valueOf(y - facilitiesTop) / facilitiesBottom, 0);
            }

            LogUtil.d("onScroll", "reserveTop:" + mReserveLayout.getTop() + "reserveBottom:" + mReserveLayout.getBottom());
            LogUtil.d("onScroll", "activeTop:" + mActiveLayout.getTop() + "activeBottom:" + mActiveLayout.getBottom());
            LogUtil.d("onScroll", "tabBottom:" + y);
        });
        mllroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentActivity.launcher(getContext());
            }
        });
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.images)
    public void onViewClicked() {
        AlbumActivity.launcher(getContext());
    }
}
