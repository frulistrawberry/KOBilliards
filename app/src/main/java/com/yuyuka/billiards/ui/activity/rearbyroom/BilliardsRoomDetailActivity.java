package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.nearbyroom.BilliardsRoomCoachFragment;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.ScreenUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.ObservableNestedScrollView;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BilliardsRoomDetailActivity extends BaseActivity {


    private String[] mTitle = {"球台预定","球房活动","球友点评","设施亮点"};
    private String[] mDateTitle;
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
    @BindView(R.id.tab_layout_reserve)
    MagicIndicator mReserveTabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.ll_room_desc)
    LinearLayout mllroom;

    public static void launcher(Context context){
        Intent intent = new Intent(context,BilliardsRoomDetailActivity.class);
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
                return  mTitle.length;
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
                    int facilitiesTop = mFacilitiesLayout.getTop()-100;
                    int commentTop = mCommentLayut.getTop();
                    mAppbarLayout.setExpanded(false);
                    switch (index){
                        case 0:
                            mScrollView.scrollTo(0,reserveTop);
                            break;
                        case 1:
                            mScrollView.scrollTo(0,activeTop);
                            break;
                        case 2:
                            mScrollView.scrollTo(0,commentTop);
                            break;
                        case 3:
                            mScrollView.scrollTo(0,facilitiesTop);
                            break;
                    }
                });
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

        CommonNavigator weekNavigator = new CommonNavigator(getContext());
        weekNavigator.setAdjustMode(true);
        weekNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return  mDateTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.text_color_3));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.text_color_6));
                simplePagerTitleView.setText(mDateTitle[index]);
                simplePagerTitleView.setTextSize(12);
                simplePagerTitleView.setSingleLine(false);
                simplePagerTitleView.setGravity(Gravity.CENTER);
                simplePagerTitleView.setPadding(0,0,0, SizeUtils.dp2px(getContext(),5));
                simplePagerTitleView.setOnClickListener(v -> {
                    mReserveTabLayout.onPageSelected(index);
                    mReserveTabLayout.onPageScrolled(index,0,0);
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.reserve_tab_indicator_start_color),getResourceColor(R.color.reserve_tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mReserveTabLayout.setNavigator(weekNavigator);
        List<Fragment> coachList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            coachList.add(new BilliardsRoomCoachFragment());
        }
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(this)));
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),coachList));
        mScrollView.setScrollViewListener((scrollView, x, y, oldx, oldy) -> {
            int reserveTop = mReserveLayout.getTop();
            int reserveBottom = mReserveLayout.getBottom();
            int activeTop = mActiveLayout.getTop();
            int activeBottom = mStarLayout.getBottom();
            int facilitiesTop = mFacilitiesLayout.getTop()-100;
            int facilitiesBottom = mFacilitiesLayout.getBottom()-100;
            int commentBottom = mCommentLayut.getBottom()-100;
            int commentTop = mCommentLayut.getTop();

            if (y>=reserveTop && y<=reserveBottom){
                mTabLayout.onPageSelected(0);
                mTabLayout.onPageScrolled(0,0,0);

            }
            if (y>=activeTop && y<=activeBottom){
                mTabLayout.onPageSelected(1);
                mTabLayout.onPageScrolled(1,0,0);

            }

            if (y>=commentTop && y<=commentBottom){
                mTabLayout.onPageSelected(2);
                mTabLayout.onPageScrolled(2,0,0);

            }

            if (y>=facilitiesTop && y<=facilitiesBottom){
                mTabLayout.onPageSelected(3);
                mTabLayout.onPageScrolled(3,0,0);

            }

        });
    }

    @Override
    protected void initData() {
        mDateTitle = getDateTitles();
    }

    @OnClick(R.id.rl_comment)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_comment:
                CommentActivity.launcher(this);
                break;
        }
    }

    private String[] getDateTitles(){
        String[] result = new String[5];
        long today = System.currentTimeMillis();
        for (int i = 0; i < result.length; i++) {
            long temp = 60*60*24*1000*i+today;
            String date = DateUtils.date2Str(new Date(temp),DateUtils.MM_DD);
            String week = DateUtils.date2Str(new Date(temp),DateUtils.EEEE);
            if (i==0)
                week = "今天";
            result[i] = date+"\n"+week;
        }
        return result;
    }
}
