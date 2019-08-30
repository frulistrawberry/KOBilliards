package com.yuyuka.billiards.ui.adapter.ko;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.course.KOListContract;
import com.yuyuka.billiards.mvp.presenter.course.KOListPresenter;
import com.yuyuka.billiards.pojo.KOListPojo;

import java.util.List;

public class KOListActivity extends BaseListActivity<KOListPresenter> implements KOListContract.IKOListView {

    private int id;
    private String title;


    public static void launcher(Context context,int id,String title){
        Intent intent = new Intent(context,KOListActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getKOList(id,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        getPresenter().getKOList(id,mCurrentPage);
    }

    @Override
    protected KOListPresenter getPresenter() {
        return new KOListPresenter(this);
    }

    @Override
    protected void initData() {
        mAdapter = new KOListAdapter();
        title = getIntent().getStringExtra("title");
        id = getIntent().getIntExtra("id",0);
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.include_ptr_recycler);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        getTitleBar().setTitle(title).showBack().show();
        onRefresh();
    }

    @Override
    public void showKOList(List<KOListPojo> data) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(data);
        }else {
            mAdapter.addData(data);
        }
    }
}
