package com.yuyuka.billiards.ui.fragment.room;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.mvp.contract.room.RecommendRoomContract;
import com.yuyuka.billiards.mvp.presenter.room.RecommendRoomPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.adapter.room.RoomAdapter;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RecommendRoomFragment extends BaseListFragment<RecommendRoomPresenter> implements RecommendRoomContract.IRecommendRoomView, AMapLocationListener {
    @BindView(R.id.ll_sort_level)
    LinearLayout mLevelSortLayout;
    @BindView(R.id.ll_sort_nearby)
    LinearLayout mNearbySortLayout;
    @BindView(R.id.ll_filter)
    LinearLayout mFilterLayout;
    @BindView(R.id.iv_back_to_top)
    ImageView mBackToTopIv;
    @BindView(R.id.ll_sort_parent)
    LinearLayout mSortParentLayout;


    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    double lat;
    double lng;



    private boolean isHeaderOpened = true;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_recommend_room,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new RoomAdapter();
        mLocationClient = new AMapLocationClient(getContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (mRecyclerView.isNestedScrollingEnabled())
                    return false;
                else
                    return super.checkCanDoRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();

            }
        });

    }

    @Override
    protected void onRefresh() {
        mCurrentPage=1;
        mPresenter.getRecommendRoomList(lat,lng,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendRoomList(lat,lng,mCurrentPage);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }



    @Subscribe
    public void onEvent(OffsetChangeEvent event){
        if (event.from.equals("NearbyRoomActivity")){
            isHeaderOpened = event.state== AppBarStateChangeListener.State.EXPANDED;
            if (event.state != AppBarStateChangeListener.State.COLLAPSED){
                mBackToTopIv.setVisibility(View.INVISIBLE);
            }else {
                mBackToTopIv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected RecommendRoomPresenter getPresenter() {
        return new RecommendRoomPresenter(this);
    }

    @Override
    public void showRecommendRoomList(List<BilliardsRoomPojo> roomList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(roomList);
        }else {
            mAdapter.addData(roomList);
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
