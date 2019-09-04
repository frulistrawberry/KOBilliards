package com.yuyuka.billiards.ui.activity.match;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.merchant.NearbyMerchantActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.match.RecommendMatchFragment;
import com.yuyuka.billiards.ui.fragment.merchant.RecommendMerchantFragment;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class NearbyMatchActivity extends BaseActivity {
    PagerAdapter mAdapter;
    String[] mTitles = {"推荐比赛","我的收藏"};
    List<Fragment> mFragmentList;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.et_search)
    EditText mSearchEt;
    @BindView(R.id.tv_sort_distance)
    TextView mDistanceSortTv;
    @BindView(R.id.tv_sort_bonus)
    TextView mBonusSortTv;
    @BindView(R.id.iv_sort_distance)
    ImageView mDistanceSortIv;
    @BindView(R.id.iv_sort_bonus)
    ImageView mBonusSortIv;
    @BindView(R.id.tv_sort_time)
    TextView mTimeSortTv;
    @BindView(R.id.iv_sort_time)
    ImageView mTimeSortIv;
    private int sortCondition = 1;
    private int mCurrentPage;

    public static void launcher(Context context){
        context.startActivity(new Intent(context, NearbyMatchActivity.class));
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_nearby_match);
        mStatusBar.setVisibility(View.GONE);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout, mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPage = i;
                if (i == 0){
                    mSearchEt.setFocusable(true);
                    mSearchEt.setFocusableInTouchMode(true);
                }else {
                    mSearchEt.setFocusable(false);
                    mSearchEt.setFocusableInTouchMode(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mSearchEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //关闭软键盘
                KeyboardUtils.hide(getContext(),mSearchEt);
                String keywords = mSearchEt.getText().toString();
                ((RecommendMatchFragment)mFragmentList.get(0)).search(keywords);
                return true;
            }
            return false;
        });
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new RecommendMatchFragment());
        mFragmentList.add(new RecommendMatchFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }


    @OnClick({R.id.ll_sort_nearby,R.id.ll_sort_bonus,R.id.ll_sort_time,R.id.iv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_sort_nearby:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mBonusSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mBonusSortIv.setImageResource(R.mipmap.ic_arrow_down);
                mTimeSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mTimeSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 1){
                    sortCondition = 2;
                    mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 1;
                    mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                ((RecommendMatchFragment)mFragmentList.get(mCurrentPage)).order(sortCondition);
                break;
            case R.id.ll_sort_bonus:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mBonusSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                mTimeSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mTimeSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 3){
                    sortCondition = 4;
                    mBonusSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 3;
                    mBonusSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                ((RecommendMatchFragment)mFragmentList.get(mCurrentPage)).order(sortCondition);
                break;
            case R.id.ll_sort_time:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mBonusSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                mTimeSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mBonusSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 5){
                    sortCondition = 6;
                    mTimeSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 5;
                    mTimeSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                ((RecommendMatchFragment)mFragmentList.get(mCurrentPage)).order(sortCondition);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
