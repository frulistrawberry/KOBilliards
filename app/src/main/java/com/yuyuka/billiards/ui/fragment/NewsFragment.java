package com.yuyuka.billiards.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.ui.activity.news.ReleaseArticleActivity;
import com.yuyuka.billiards.ui.activity.news.VideoPickerActivity;
import com.yuyuka.billiards.ui.adapter.common.NavigatorAdapter;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.adapter.news.VideoPickAdapter;
import com.yuyuka.billiards.ui.fragment.news.NewsListFragment;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.ListPop;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.ViewPagerHelper;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    MagicIndicator mIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    List<Fragment> mFragmentList;
    PagerAdapter mAdapter;
    String[] mTitles = {"关注","推荐","文章","视频","小视频"};
    public ListPop mListPop;
    @BindView(R.id.et_search)
     EditText mSearchEt;
    private String keywords="";

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_news,parent,false);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsListFragment.newFragment(5));
        mFragmentList.add(NewsListFragment.newFragment(3));
        mFragmentList.add(NewsListFragment.newFragment(0));
        mFragmentList.add(NewsListFragment.newFragment(2));
        mFragmentList.add(NewsListFragment.newFragment(1));
        mAdapter = new PagerAdapter(getChildFragmentManager(),mFragmentList,mTitles);
    }

    @Override
    protected void initView() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new NavigatorAdapter(mFragmentList,mViewPager,mAdapter));
        mIndicator.setNavigator(commonNavigator);
        mViewPager.setAdapter(mAdapter);
        ViewPagerHelper.bind(mIndicator, mViewPager);
        mListPop = new ListPop(getContext()).createPopup();
        List<String> popList = new ArrayList<>();
        popList.add("发图文");
        popList.add("写文章");
        popList.add("拍小视频");
        popList.add("发视频");
        mListPop.setData(popList);
        mListPop.setOnItemClickListener(position -> {
            switch (position){
                case 1:
                    startActivity(new Intent(getContext(),ReleaseArticleActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getContext(), VideoPickerActivity.class));
                    break;
            }
        });
        mSearchEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //关闭软键盘
                KeyboardUtils.hide(getContext(),mSearchEt);
                keywords = mSearchEt.getText().toString();
                ((NewsListFragment)mFragmentList.get(mViewPager.getCurrentItem())).onRefresh(keywords);
                return true;
            }
            return false;
        });
        mSearchEt.clearFocus();
    }

    @OnClick(R.id.iv_contact)
    public void onClick(View v){
        mListPop.showAtAnchorView(v, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, SizeUtils.dp2px(getContext(), -15));
    }
}
