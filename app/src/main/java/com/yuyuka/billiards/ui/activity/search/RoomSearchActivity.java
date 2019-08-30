package com.yuyuka.billiards.ui.activity.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.search.SearchContract;
import com.yuyuka.billiards.mvp.presenter.search.IndexSearchPresenter;

import butterknife.BindView;

public class RoomSearchActivity extends BaseListActivity<IndexSearchPresenter> implements SearchContract.ISearchView, AMapLocationListener {

    @BindView(R.id.ll_default)
    LinearLayout mDefaultLayout;
    int style;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private double lat;
    private double lng;
    public static void launcher(Context context,int titleStyle){
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra("titleStyle",titleStyle);

        context.startActivity(intent);
    }

    public static void launcher(Context context){
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra("titleStyle",0);

        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        setTitleStyle(style);
        setContentView(R.layout.activity_billiards_room_search);
        mStatusBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRefresh() {
        getPresenter().searchIndex("球",lat,lng,mCurrentPage);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;

    }

    @Override
    protected void initData() {
        style = getIntent().getIntExtra("titleStyle",0);
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    protected IndexSearchPresenter getPresenter() {
        return new IndexSearchPresenter(this);
    }


    @Override
    public void showResultList() {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        lat = aMapLocation.getLatitude();
        lng = aMapLocation.getLongitude();
        onRefresh();
    }
}
