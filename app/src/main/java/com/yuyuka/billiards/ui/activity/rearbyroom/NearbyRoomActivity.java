package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.ui.adapter.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.nearbyroom.CollectionRoomFragment;
import com.yuyuka.billiards.ui.fragment.nearbyroom.RecommendRoomFragment;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.NoScrollViewPager;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick({R.id.fl_auth,R.id.fl_save,R.id.fl_rank,R.id.fl_coach,R.id.iv_map})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.fl_auth:
                BilliardsRoomListActivity.launcher(this,"KO认证");
                break;
            case R.id.fl_save:
                BilliardsRoomListActivity.launcher(this,"今日特价");
                break;
            case R.id.fl_rank:
                BilliardsRoomListActivity.launcher(this,"热门榜单");
                break;
            case R.id.fl_coach:
                BilliardsCoachListActivity.launcher(this);
                break;
            case R.id.iv_map:
                MapActivity.launcher(this);
                break;
        }
    }
}
