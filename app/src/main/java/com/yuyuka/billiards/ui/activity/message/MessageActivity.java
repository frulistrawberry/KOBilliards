package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.message.AddressbookFragment;
import com.yuyuka.billiards.ui.fragment.message.GroupFragment;
import com.yuyuka.billiards.ui.fragment.message.MessageFragment;
import com.yuyuka.billiards.ui.fragment.message.RankingListFragment;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    MagicIndicator tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    PagerAdapter mAdapter;
    String[] mTitles = {"消息", "通讯录", "群组", "排行榜"};
    List<Fragment> mFragmentList;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_imga)
    ImageView ivTitleRight;
    @BindView(R.id.v_title_divider)
    View vTitleDivider;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, MessageActivity.class));
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_eessageactivity);
        viewPager.setAdapter(mAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList, viewPager, mAdapter));
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MessageFragment());
        mFragmentList.add(new AddressbookFragment());
        mFragmentList.add(new GroupFragment());
        mFragmentList.add(new RankingListFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(), mFragmentList, mTitles);
    }
    @OnClick(R.id.iv_imga)
    public void onViewClicked() {
        SetActivity.launcher(this);
    }
}
