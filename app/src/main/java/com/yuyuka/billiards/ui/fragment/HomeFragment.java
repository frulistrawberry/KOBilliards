package com.yuyuka.billiards.ui.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.base.BaseMvpFragment;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.mvp.contract.HomeContract;
import com.yuyuka.billiards.mvp.presenter.HomePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.ImagePojo;
import com.yuyuka.billiards.pojo.ModularPojo;
import com.yuyuka.billiards.ui.activity.bonus.BonusPoolActivity;
import com.yuyuka.billiards.ui.activity.facetoface.BattleWaitActivity;
import com.yuyuka.billiards.ui.activity.scan.ScanActivity;
import com.yuyuka.billiards.ui.activity.search.RoomSearchActivity;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.news.NewsListFragment;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CollectionUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import io.reactivex.Single;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.IHomeView {
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
    @BindView(R.id.v_status)
    View statusbar;

    List<Fragment> mFragmentList;
    PagerAdapter mAdapter;
    boolean canRefresh = true;
    @BindView(R.id.iv_table)
    ImageView tableIv;
    boolean isFabAnimg;

    int state = 0;

    String[] mTitles = {"推荐"};
    String[] mModularTitles = {"附近比赛","附近球房","个人模式","面对面对战","添加商户","KO学堂","台球二手","排行榜"};
    int[] mModularIcons = {R.mipmap.ic_modular_nearby_match,R.mipmap.ic_modular_nearby_room,
            R.mipmap.ic_modular_bet, R.mipmap.ic_modular_face_to_face,R.mipmap.ic_modular_add_merchant,
            R.mipmap.ic_modular_cause,R.mipmap.ic_modular_mail,R.mipmap.ic_modular_rank};

    private Animator hideAnimator;
    private Animator showAnimator;

    boolean isFabShow;

    // 动画隐藏浮动按钮
    private void hideFabAnim() {
        state = 0;
        float x = tableIv.getWidth()/2.2f;
        if (showAnimator != null && showAnimator.isRunning()) {
            showAnimator.cancel();
            isFabAnimg = false;
        }
        if (isFabAnimg) {
            return;
        }
        hideAnimator = ObjectAnimator.ofFloat(tableIv, "translationX", 0f, -x);
        hideAnimator.setDuration(500);
        hideAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isFabAnimg = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isFabAnimg = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isFabAnimg = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        hideAnimator.start();
    }

    // 动画显示浮动按钮
    private void showFabAnim() {
        state = 1;
        if (hideAnimator != null && hideAnimator.isRunning()) {
            hideAnimator.cancel();
            isFabAnimg = false;
        }
        if (isFabAnimg) {
            return;
        }

        float x = tableIv.getWidth()/2.2f;
        showAnimator = ObjectAnimator.ofFloat(tableIv, "translationX", -x, 0f);
        showAnimator.setDuration(500);
        showAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isFabAnimg = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                isFabAnimg = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isFabAnimg = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        showAnimator.start();
    }



    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_home,parent,false);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsListFragment.newFragment(4,true));
        mAdapter = new PagerAdapter(getChildFragmentManager(),mFragmentList,mTitles);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        statusbar.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(getActivity())));

        mRoot.setOnTouchListener((view, motionEvent) -> {
            if (tableIv.getVisibility() == View.VISIBLE){
                hideFabAnim();
            }
            return false;
        });
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

                if (tableIv.getVisibility() == View.VISIBLE && (HomeFragment.this.state == 1 || HomeFragment.this.state == 2)){
                    hideFabAnim();
                }

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
        bannerData.add(new ImagePojo("homebanner1.png"));
        bannerData.add(new ImagePojo("homebanner2.png"));
        bannerData.add(new ImagePojo("homebanner3.png"));
        ViewUtils.loadBanner(bannerData,mBanner,true,true,false);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mIndicator.setNavigator(commonNavigator);
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



    @OnClick({R.id.btn_top,R.id.btn_bonus_rewards,R.id.iv_msg,R.id.ll_scan,R.id.btn_search,R.id.btn_search1,R.id.iv_table})
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
             case R.id.iv_msg:
                 break;
            case R.id.ll_scan:
                Intent intent = new Intent(getContext(), ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_search:
                RoomSearchActivity.launcher(getContext());
                break;
            case R.id.btn_search1:
                RoomSearchActivity.launcher(getContext());
                break;
            case R.id.iv_table:
                if (state == 0){
                    state =1;
                    showFabAnim();
                }else if (state == 1){
                    state = 2;
                    getPresenter().myTable();
                }else {
                    state = 0;
                    hideFabAnim();
                }
                break;
        }
    }

    boolean isOnResume = true;

    @Override
    public void onResume() {
        super.onResume();
        isOnResume = true;
        getPresenter().getBattle();
    }

    @Override
    public void onStop() {
        super.onStop();
        isOnResume = false;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void showTable(List<CustomNoticePojo.Battle> data) {
        if (CollectionUtils.isEmpty(data)){
            tableIv.setVisibility(View.GONE);
        }else {
            tableIv.setVisibility(View.VISIBLE);
            hideFabAnim();
        }
    }
}
