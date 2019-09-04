package com.yuyuka.billiards.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.pojo.ImagePojo;
import com.yuyuka.billiards.pojo.ModularPojo;
import com.yuyuka.billiards.ui.activity.bonus.BonusPoolActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.news.NewsListFragment;
import com.yuyuka.billiards.utils.ViewUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.CircleIndicator;
import com.yuyuka.billiards.widget.ModularPager;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class HomeFragment extends BaseFragment{
    @BindView(R.id.app_bar)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.bg_content)
    View mBgContentView;
    @BindView(R.id.toolbar_open)
    RelativeLayout mToolbarOpenLayout;
    @BindView(R.id.toolbar_close)
    RelativeLayout mToolbarCloseLayout;
    @BindView(R.id.vp_modular)
    ModularPager mModularPager;
    @BindView(R.id.ci_modular)
    CircleIndicator mModularIndicator;
    @BindView(R.id.banner)
    ConvenientBanner<ImagePojo> mBanner;
    @BindView(R.id.tab_layout)
    MagicIndicator mIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.fl_header)
    FrameLayout mHeaderLayout;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout mPtrLayout;

    List<Fragment> mFragmentList;
    PagerAdapter mAdapter;
    boolean canRefresh = true;

    String[] mTitles = {"推荐","附近","视频"};
    String[] mModularTitles = {"附近比赛","附近球房","个人模式","面对面对战","添加商户","KO学堂","台球二手","排行榜"};
    int[] mModularIcons = {R.mipmap.ic_modular_nearby_match,R.mipmap.ic_modular_nearby_room,
            R.mipmap.ic_modular_bet, R.mipmap.ic_modular_face_to_face,R.mipmap.ic_modular_add_merchant,
            R.mipmap.ic_modular_cause,R.mipmap.ic_modular_mail,R.mipmap.ic_modular_rank};




    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_home,parent,false);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsListFragment.newFragment(4,true));
        mFragmentList.add(NewsListFragment.newFragment(3,true));
        mFragmentList.add(NewsListFragment.newFragment(2,true));
        mAdapter = new PagerAdapter(getChildFragmentManager(),mFragmentList,mTitles);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                LogUtil.d("onStateChanged",state);
                if (state == State.COLLAPSED){
                    mToolbarCloseLayout.setVisibility(View.VISIBLE);
                    mToolbarOpenLayout.setVisibility(View.GONE);
                }else {
                    mToolbarOpenLayout.setVisibility(View.VISIBLE);
                    mToolbarCloseLayout.setVisibility(View.GONE);
                }
                canRefresh = state == State.EXPANDED;



            }
        });
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ((BaseListFragment)mFragmentList.get(mViewPager.getCurrentItem())).onRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return canRefresh;
            }
        });
        List<ModularPojo> modularPojos = new ArrayList<>();
        for (int i = 0; i < mModularTitles.length; i++) {
            modularPojos.add(new ModularPojo(mModularIcons[i],mModularTitles[i]));
        }
        if (mModularTitles.length<=8)
            mModularIndicator.setVisibility(View.GONE);
        mModularPager.setData(modularPojos);
        mModularIndicator.setViewPager(mModularPager);
        List<ImagePojo> bannerData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bannerData.add(new ImagePojo());
        }
        ViewUtils.loadBanner(bannerData,mBanner,true,true,false);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mIndicator.setNavigator(commonNavigator);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(mIndicator, mViewPager);

    }


    @Subscribe
    public void onEvent(String event){
        if (event.equals("refresh_complete")){
            if (mPtrLayout.isRefreshing())
                mPtrLayout.refreshComplete();
        }
    }

    @OnClick({R.id.btn_top,R.id.btn_bonus_rewards})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_top:
                mToolbarOpenLayout.setVisibility(View.VISIBLE);
                mHeaderLayout.setVisibility(View.VISIBLE);
                mAppbarLayout.setExpanded(true);
                break;
            case R.id.btn_bonus_rewards:
                startActivity(new Intent(getContext(),BonusPoolActivity.class));
                break;
        }
    }






}
