package com.yuyuka.billiards.ui.fragment.merchant;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
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
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.event.OffsetChangeEvent;
import com.yuyuka.billiards.mvp.contract.merchant.RecommendRoomContract;
import com.yuyuka.billiards.mvp.presenter.merchant.RecommendRoomPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.activity.merchant.NearbyMerchantActivity;
import com.yuyuka.billiards.ui.adapter.room.RoomAdapter;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RecommendMerchantFragment extends BaseListFragment<RecommendRoomPresenter> implements RecommendRoomContract.IRecommendRoomView, AMapLocationListener {
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
    int sortCondition = 1;
    String keywords = "";
    @BindView(R.id.tv_sort_distance)
    TextView mDistanceSortTv;
    @BindView(R.id.tv_sort_level)
    TextView mLevelSortTv;
    @BindView(R.id.iv_sort_distance)
    ImageView mDistanceSortIv;
    @BindView(R.id.iv_sort_level)
    ImageView mLevelSortIv;


    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    double lat;
    double lng;



    private boolean canRefresh;

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
                return canRefresh && super.checkCanDoRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();

            }
        });

    }

    @Override
    public void onRefresh() {
        mCurrentPage=0;
        mPresenter.getRecommendRoomList(keywords,lat,lng,sortCondition,mCurrentPage);
    }

    public void search(String keywords){
        this.keywords = keywords;
        mAdapter.setNewData(null);
        onRefresh();
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendRoomList(keywords,lat,lng,sortCondition,mCurrentPage);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }



    @Subscribe
    public void onEvent(OffsetChangeEvent event){
        if (event.from.equals("NearbyMerchantActivity")){
            canRefresh = event.state == AppBarStateChangeListener.State.EXPANDED;
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
        if (mCurrentPage == 0){
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





    @OnClick({R.id.iv_back_to_top,R.id.ll_sort_nearby,R.id.ll_sort_level})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back_to_top:
                mRecyclerView.scrollToPosition(0);
                ((NearbyMerchantActivity) Objects.requireNonNull(getActivity())).expand();
                break;
            case R.id.ll_sort_nearby:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mLevelSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mLevelSortIv.setImageResource(R.mipmap.ic_arrow_down);
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
            case R.id.ll_sort_level:
                mDistanceSortTv.setTextColor(getResourceColor(R.color.text_color_3));
                mLevelSortTv.setTextColor(getResourceColor(R.color.text_color_1));
                mDistanceSortIv.setImageResource(R.mipmap.ic_arrow_down);
                if (sortCondition == 3){
                    sortCondition = 4;
                    mLevelSortIv.setImageResource(R.mipmap.ic_arrow_up);
                }else {
                    sortCondition = 3;
                    mLevelSortIv.setImageResource(R.mipmap.ic_arrow_down);
                }
                mAdapter.setNewData(null);
                onRefresh();
                break;
        }
    }
}
