package com.yuyuka.billiards.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.app_bar)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.bg_content)
    View mBgContentView;
    @BindView(R.id.toolbar_open)
    RelativeLayout mToolbarOpenLayout;
    @BindView(R.id.toolbar_close)
    RelativeLayout mToolbarCloseLayout;
    @BindView(R.id.bg_toolbar_open)
    View mBgToolbarOpenView;
    @BindView(R.id.bg_toolbar_close)
    View mBgToolbarCloseView;



    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_home,parent,false);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        mAppbarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //垂直方向偏移量
        int offset = Math.abs(verticalOffset);
        //最大偏移距离
        int scrollRange = appBarLayout.getTotalScrollRange();
        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
            mToolbarOpenLayout.setVisibility(View.VISIBLE);
            mToolbarCloseLayout.setVisibility(View.GONE);
            //根据偏移百分比 计算透明值
            float scale2 = (float) offset / (scrollRange / 2);
            int alpha2 = (int) (255 * scale2);
            mBgToolbarOpenView.setBackgroundColor(Color.argb(alpha2, 29, 183, 209));
        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
            mToolbarCloseLayout.setVisibility(View.VISIBLE);
            mToolbarOpenLayout.setVisibility(View.GONE);
            float scale3 = (float) (scrollRange - offset) / (scrollRange / 2);
            int alpha3 = (int) (255 * scale3);
            mBgToolbarCloseView.setBackgroundColor(Color.argb(alpha3, 29, 183, 209));
        }
        //根据偏移百分比计算扫一扫布局的透明度值
        float scale = (float) offset / scrollRange;
        int alpha = (int) (255 * scale);
        mBgContentView.setBackgroundColor(Color.argb(alpha, 29, 183, 209));
    }
}
