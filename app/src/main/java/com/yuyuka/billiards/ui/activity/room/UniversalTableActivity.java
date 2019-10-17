package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.message.AddressbookFragment;
import com.yuyuka.billiards.ui.fragment.message.GroupFragment;
import com.yuyuka.billiards.ui.fragment.message.MessageFragment;
import com.yuyuka.billiards.ui.fragment.roomball.PreliminaryFragment;
import com.yuyuka.billiards.ui.fragment.roomball.SemifinalFragement;
import com.yuyuka.billiards.ui.fragment.roomball.finalFragement;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class UniversalTableActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    MagicIndicator tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    PagerAdapter mAdapter;
    String[] mTitles = {"预选赛", "32进16", "16进8", "8进4","半决赛","决赛"};
    List<Fragment> mFragmentList;
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, UniversalTableActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("普级表").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_universaltable);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,viewPager,mAdapter));
        tabLayout.setNavigator(commonNavigator);
        viewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(tabLayout, viewPager);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new PreliminaryFragment());
        mFragmentList.add(new MessageFragment());
        mFragmentList.add(new AddressbookFragment());
        mFragmentList.add(new GroupFragment());
        mFragmentList.add(new SemifinalFragement());
        mFragmentList.add(new finalFragement());
        mAdapter = new PagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles);
    }
}
