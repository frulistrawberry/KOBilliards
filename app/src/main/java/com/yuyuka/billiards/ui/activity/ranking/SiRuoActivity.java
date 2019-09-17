package com.yuyuka.billiards.ui.activity.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.ranking.SiRouJibaiFragment;
import com.yuyuka.billiards.ui.fragment.ranking.SiRouZhanliFragment;
import com.yuyuka.billiards.ui.fragment.ranking.SiRuoDuanweiFragment;
import com.yuyuka.billiards.ui.fragment.ranking.SiRuoZhiyeBangFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SiRuoActivity extends BaseActivity {
    PagerAdapter mAdapter;
    String[] mTitles = {"段位榜","战力榜","击败榜","职业榜"};
    List<Fragment> mFragmentList;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.v_title_divider)
    View vTitleDivider;
    @BindView(R.id.tab_layout)
    MagicIndicator tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    public static void launcher(Context context){
        context.startActivity(new Intent(context, SiRuoActivity.class));
    }
    @Override
    protected void initView() {
       setContentView(R.layout.layout_siruoactivity);
        viewPager.setAdapter(mAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,viewPager,mAdapter));
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new SiRuoDuanweiFragment());
        mFragmentList.add(new SiRouZhanliFragment());
        mFragmentList.add(new SiRouJibaiFragment());
        mFragmentList.add(new SiRuoZhiyeBangFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList,mTitles);
    }
}
