package com.yuyuka.billiards.ui.activity.market;

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
import com.yuyuka.billiards.mvp.contract.market.GoodsListContract;
import com.yuyuka.billiards.mvp.presenter.market.GoodsListPresenter;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.ui.activity.search.RoomSearchActivity;
import com.yuyuka.billiards.ui.adapter.goods.GoodsAdapter;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.EasyPopup;
import com.yuyuka.billiards.widget.dialog.FilterDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MarketActivity extends BaseListActivity<GoodsListPresenter> implements GoodsListContract.IGoodsListView {


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
    private FilterDialog mFilterPop;
    String keywords;
    int sortCondition;
    int typeCondition;
    int quickCondition;
    int lowPrice;
    int highPrice;
    int releaseTimeCondition;
    int otherCondition;

    public static void launcher(Context context){
        Intent intent = new Intent(context, MarketActivity.class);
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
        initFilterPop();
        onRefresh();
    }


    @Override
    public void onRefresh() {
        mCurrentPage = 0;
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
        if (mCurrentPage == 0){
            mAdapter.setNewData(dataList);
        }else {
            mAdapter.addData(dataList);
        }
    }

    @OnClick({R.id.ll_sort,R.id.ll_location,R.id.ll_filter,R.id.btn_search,R.id.btn_release})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_sort:
                mSortIv.setImageResource(R.mipmap.ic_arrow_up);
                break;
            case R.id.ll_location:
                mLocationIv.setImageResource(R.mipmap.ic_arrow_up);
                break;
            case R.id.ll_filter:
                mFilterPop.show();
                break;
            case R.id.btn_search:
                RoomSearchActivity.launcher(this,1);
                break;
            case R.id.btn_release:
                startActivity(new Intent(this,ReleaseGoodsActivity.class));
                break;
        }
    }

    private void initFilterPop() {
        mFilterPop = new FilterDialog(this);

    }



}
