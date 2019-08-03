package com.yuyuka.billiards.ui.activity.mail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.goods.GoodsListContract;
import com.yuyuka.billiards.mvp.presenter.goods.GoodsListPresenter;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.ui.adapter.goods.GoodsAdapter;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.EasyPopup;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MailActivity extends BaseListActivity<GoodsListPresenter> implements GoodsListContract.IGoodsListView {


    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.ll_sort_parent)
    LinearLayout mSortParentLayout;
    @BindView(R.id.tv_sort)
    TextView mSortTv;
    @BindView(R.id.iv_sort)
    ImageView mSortIv;
    @BindView(R.id.tv_location)
    TextView mLocationTv;
    @BindView(R.id.iv_location)
    ImageView mLocationIv;
    boolean isAppbarOpen;
    private int sort;
    private int location;
    private EasyPopup mSortPop;
    private EasyPopup mLocationPop;
    private EasyPopup mFilterPop;

    public static void launcher(Context context){
        Intent intent = new Intent(context,MailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("二手市场").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_mail);
        super.initView();
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return isAppbarOpen && super.checkCanDoRefresh(frame, content, header);
            }
        });
        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                isAppbarOpen = state == State.EXPANDED;
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(mAdapter);
        initSortPop();
        initLocationPop();
        onRefresh();
    }



    @Override
    protected void onRefresh() {
        mCurrentPage = 1;
        getPresenter().getGoodsList(mCurrentPage);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage ++;
        mPresenter.getGoodsList(mCurrentPage);
    }

    @Override
    protected void initData() {
        mAdapter = new GoodsAdapter();
    }

    @Override
    protected GoodsListPresenter getPresenter() {
        return new GoodsListPresenter(this);
    }

    @Override
    public void showGoodsList(List<GoodsPojo> dataList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(dataList);
        }else {
            mAdapter.addData(dataList);
        }
    }

    @OnClick({R.id.ll_sort,R.id.ll_location,R.id.ll_filter})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_sort:
                mSortIv.setImageResource(R.mipmap.ic_arrow_up);
                mSortPop.showAsDropDown(v);
                break;
            case R.id.ll_location:
                mLocationIv.setImageResource(R.mipmap.ic_arrow_up);
                mLocationPop.showAsDropDown(v);
                break;
            case R.id.ll_filter:
                break;
        }
    }

    private void initSortPop() {
        mSortPop = new EasyPopup(this)
                .setContentView(R.layout.pop_goods_sort)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setFocusAndOutsideEnable(true).createPopup();
        LinearLayout parent = (LinearLayout) mSortPop.getContentView();
        parent.setOnClickListener(v -> {
            mSortPop.dismiss();
        });
        for (int i = 0; i < parent.getChildCount(); i++) {
            LinearLayout item = (LinearLayout) parent.getChildAt(i);
            resetSortItem(item);
            item.setOnClickListener(v -> {
                for (int i1 = 0; i1 < parent.getChildCount(); i1++) {
                    resetSortItem((LinearLayout) parent.getChildAt(i1));
                }
                selectSortItem(item);
            });

        }

        mSortPop.setOnDismissListener(() -> {
            mSortIv.setImageResource(R.mipmap.ic_arrow_down);
        });

    }

    private void resetSortItem(LinearLayout item){
        TextView textView = (TextView) item.getChildAt(0);
        ImageView imageView = (ImageView) item.getChildAt(1);
        textView.setTextColor(getResourceColor(R.color.text_color_3));
        imageView.setVisibility(View.GONE);
    }

    private void selectSortItem(LinearLayout item){
        TextView textView = (TextView) item.getChildAt(0);
        ImageView imageView = (ImageView) item.getChildAt(1);
        textView.setTextColor(getResourceColor(R.color.text_color_1));
        imageView.setVisibility(View.VISIBLE);
        sort = DataOptionUtils.getGoodsSortParam(textView.getText().toString());
        mSortTv.setText(textView.getText());
        mSortPop.dismiss();
        onRefresh();
    }

    private void initLocationPop() {
        mLocationPop = new EasyPopup(this)
                .setContentView(R.layout.pop_goods_location)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                .setFocusAndOutsideEnable(true).createPopup();
        mLocationPop.getContentView().setOnClickListener(v -> {
            mLocationPop.dismiss();
        });

        mLocationPop.setOnDismissListener(() -> {
            mLocationIv.setImageResource(R.mipmap.ic_arrow_down);
        });
    }

}
