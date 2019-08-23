package com.yuyuka.billiards.ui.fragment.match;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.mvp.contract.match.RecommendMatchContract;
import com.yuyuka.billiards.mvp.presenter.match.RecommendMatchPresenter;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.ui.adapter.match.MatchAdapter;
import com.yuyuka.billiards.utils.CommonUtils;

import java.util.List;

public class RecommendMatchFragment extends BaseListFragment<RecommendMatchPresenter> implements  RecommendMatchContract.IRecommendMatchView, AMapLocationListener {

    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lng;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new MatchAdapter();
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onRefresh() {
        mCurrentPage=0;
        mPresenter.getRecommendMatchList(lat,lng,1,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendMatchList(lat,lng,1,mCurrentPage);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }





    @Override
    protected RecommendMatchPresenter getPresenter() {
        return new RecommendMatchPresenter(this);
    }



    @Override
    public void showRecommendMatchList(List<BilliardsMatchPojo> matchList) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(matchList);
        }else {
            mAdapter.addData(matchList);
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        lat = aMapLocation.getLatitude();
        lng = aMapLocation.getLongitude();
        CommonUtils.saveLocationInfo(lat,lng);
        onRefresh();


    }
}
