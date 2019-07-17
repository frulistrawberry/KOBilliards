package com.yuyuka.billiards.ui.fragment.nearbymatch;

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
import com.yuyuka.billiards.mvp.contract.nearbymatch.RecommendMatchContract;
import com.yuyuka.billiards.mvp.presenter.nearbymatch.RecommendMatchPresenter;
import com.yuyuka.billiards.pojo.MatchPojo;
import com.yuyuka.billiards.ui.adapter.MatchListAdapter;

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
        mAdapter = new MatchListAdapter();
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
    protected void onRefresh() {
        mCurrentPage=1;
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
    public void showRecommendMatchList(List<MatchPojo> matchList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(matchList);
        }else {
            mAdapter.addData(matchList);
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        lat = aMapLocation.getLatitude();
        lng = aMapLocation.getLongitude();
        onRefresh();


    }
}
