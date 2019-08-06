package com.yuyuka.billiards.ui.fragment.ko;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.mvp.contract.ko.KOTypeContract;
import com.yuyuka.billiards.mvp.presenter.ko.KOClassPresenter;
import com.yuyuka.billiards.pojo.KOClassPojo;
import com.yuyuka.billiards.ui.adapter.ko.KOTypeAdapter;

import java.util.List;

public class KOClassFragment extends BaseListFragment<KOClassPresenter> implements KOTypeContract.IKOTypeView {
    private int type;
    private View mFooterView;

    public static Fragment newFragment(int type){
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        KOClassFragment fragment = new KOClassFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void onRefresh() {
        getPresenter().getCrippleTypeList(type);
    }


    @Override
    protected KOClassPresenter getPresenter() {
        return new KOClassPresenter(this);
    }


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.include_ptr_recycler,parent,false);
    }

    @Override
    protected void initData() {
        type = getArguments().getInt("type");
        mAdapter = new KOTypeAdapter();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.layout_ko_footer,mRecyclerView,false);
        mAdapter.addFooterView(mFooterView);
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    public void showKOClassList(List<KOClassPojo> dataList) {
        mAdapter.setNewData(dataList);
    }
}
