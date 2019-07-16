package com.yuyuka.billiards.ui.fragment.nearbymatch;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.mvp.contract.nearbymatch.RecommendMatchContract;
import com.yuyuka.billiards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.yuyuka.billiards.mvp.presenter.nearbymatch.RecommendMatchPresenter;
import com.yuyuka.billiards.mvp.presenter.nearbyroom.CollectionRoomPresenter;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.adapter.BilliardsRoomAdapter;
import com.yuyuka.billiards.ui.adapter.MatchListAdapter;

import java.util.List;

public class RecommendMatchFragment extends BaseListFragment<RecommendMatchPresenter> implements  RecommendMatchContract.IRecommendMatchView {


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new MatchListAdapter();
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
        mPresenter.getRecommendMatchList(mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getRecommendMatchList(mCurrentPage);

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
        if (mCurrentPage == 1){
            mAdapter.setNewData(matchList);
        }else {
            mAdapter.addData(matchList);
        }
    }
}
