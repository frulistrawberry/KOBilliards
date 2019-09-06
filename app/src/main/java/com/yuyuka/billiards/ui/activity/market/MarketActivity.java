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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.market.GoodsListContract;
import com.yuyuka.billiards.mvp.presenter.market.GoodsListPresenter;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.ui.activity.search.RoomSearchActivity;
import com.yuyuka.billiards.ui.adapter.goods.GoodsAdapter;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.EasyPopup;
import com.yuyuka.billiards.widget.dialog.FilterDialog;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MarketActivity extends BaseListActivity<GoodsListPresenter> implements GoodsListContract.IGoodsListView, AMapLocationListener {


    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.ll_sort_parent)
    LinearLayout mSortParentLayout;
    boolean isAppbarOpen;
    private FilterDialog mFilterPop;
    String keywords;
    int sortCondition = 1;
    int typeCondition =-1;
    int quickCondition = -1;
    int lowPrice = -1;
    int highPrice = -1;
    int releaseTimeCondition = -1;
    int otherCondition = -1;
    double lat;
    double lng;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.tv_sort_distance)
    TextView mDistanceSortTv;
    @BindView(R.id.tv_sort_price)
    TextView mPriceSortTv;
    @BindView(R.id.iv_sort_distance)
    ImageView mDistanceSortIv;
    @BindView(R.id.iv_sort_price)
    ImageView mPriceSortIv;

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
    }


    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mPresenter.getGoodsList(keywords,lat,lng,sortCondition,typeCondition,quickCondition,lowPrice,highPrice,releaseTimeCondition,otherCondition,mCurrentPage);
    }


    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage ++;
        mPresenter.getGoodsList(keywords,lat,lng,sortCondition,typeCondition,quickCondition,lowPrice,highPrice,releaseTimeCondition,otherCondition,mCurrentPage);
    }

    @Override
    protected void initData() {
        mAdapter = new GoodsAdapter();
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
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

    @OnClick({R.id.ll_sort_nearby,R.id.ll_sort_price,R.id.ll_filter,R.id.tv_gan,R.id.tv_he,R.id.tv_zhuo,R.id.tv_other,R.id.btn_release})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_sort_nearby:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mPriceSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mPriceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 1){
                    sortCondition = 2;
                    mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 1;
                    mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.ll_sort_price:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mPriceSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 3){
                    sortCondition = 4;
                    mPriceSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 3;
                    mPriceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.ll_filter:
                mFilterPop.show();
                break;
            case R.id.tv_gan:
                typeCondition = 1;
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.tv_he:
                typeCondition = 2;
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.tv_zhuo:
                typeCondition = 3;
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.tv_other:
                typeCondition = 4;
                mAdapter.setNewData(null);
                onRefresh();
                break;
            case R.id.btn_search:
                break;
            case R.id.btn_release:
                startActivity(new Intent(this,ReleaseGoodsActivity.class));
                break;
        }
    }

    private void initFilterPop() {
        mFilterPop = new FilterDialog(this);

    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        lat = aMapLocation.getLatitude();
        lng = aMapLocation.getLongitude();
        CommonUtils.saveLocationInfo(lat,lng);
        onRefresh();
    }
}
