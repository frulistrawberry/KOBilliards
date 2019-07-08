package com.kobilliards.ui.fragment.nearbyroom;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kobilliards.R;
import com.kobilliards.base.BaseListFragment;
import com.kobilliards.event.OffsetChangeEvent;
import com.kobilliards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.kobilliards.mvp.presenter.nearbyroom.RecommendRoomPresenter;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.adapter.BilliardsRoomAdapter;
import com.kobilliards.utils.BarUtils;
import com.kobilliards.widget.AppBarStateChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RecommendRoomFragment extends BaseListFragment<RecommendRoomPresenter> implements RecommendRoomContract.IRecommendRoomView {
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
    @BindView(R.id.v_status_bar)
    View mStatusBarView;

    String sort;
    String filter;


    private boolean isHeaderOpened = true;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_recommend_room,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsRoomAdapter();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(getContext())));
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (isHeaderOpened)
                    return super.checkCanDoRefresh(frame, content, header);
                else
                    return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();

            }
        });
        onRefresh();


    }

    @Override
    protected void onRefresh() {
        mCurrentPage=1;
        mPresenter.getRecommendRoomList(sort,filter,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendRoomList(sort,filter,mCurrentPage);

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
                mStatusBarView.setVisibility(View.GONE);
                mBackToTopIv.setVisibility(View.INVISIBLE);
            }else {
                mStatusBarView.setVisibility(View.VISIBLE);
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
}
