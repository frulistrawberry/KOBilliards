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
import com.kobilliards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.kobilliards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.kobilliards.mvp.presenter.nearbyroom.CollectionRoomPresenter;
import com.kobilliards.mvp.presenter.nearbyroom.RecommendRoomPresenter;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.adapter.BilliardsRoomAdapter;
import com.kobilliards.utils.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class CollectionRoomFragment extends BaseListFragment<CollectionRoomPresenter> implements  CollectionRoomContract.ICollectionRoomView {


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsRoomAdapter();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();

    }

    @Override
    protected void onRefresh() {
        mCurrentPage=1;
        mPresenter.getRecommendRoomList(mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendRoomList(mCurrentPage);

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }





    @Override
    protected CollectionRoomPresenter getPresenter() {
        return new CollectionRoomPresenter(this);
    }

    @Override
    public void showCollectionRoomList(List<BilliardsRoomPojo> roomList) {
        if (mCurrentPage == 1){
            mAdapter.setNewData(roomList);
        }else {
            mAdapter.addData(roomList);
        }
    }


}
