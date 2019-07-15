package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.kobilliards.R;
import com.kobilliards.base.BaseListActivity;
import com.kobilliards.base.BasePresenter;
import com.kobilliards.pojo.AlbumBean;
import com.kobilliards.ui.adapter.AlbumActivityAdapter;
import com.kobilliards.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;


public class AlbumActivity extends BaseListActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<AlbumBean> data ;
    AlbumBean albumBean;
    public static void launcher(Context context) {
        Intent intent = new Intent(context, AlbumActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("相册").showBack().show();
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.include_ptr_recycler);
        for (int i=0;i<10;i++){
            albumBean= new AlbumBean();
            data.add(albumBean);
        }
        mAdapter.setNewData(data);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mAdapter = new AlbumActivityAdapter();
        data=new ArrayList<>();
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }


}
