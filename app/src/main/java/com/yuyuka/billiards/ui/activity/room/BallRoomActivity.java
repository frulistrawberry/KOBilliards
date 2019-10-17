package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.BallRoomBean;
import com.yuyuka.billiards.ui.adapter.ballroom.BallRoomAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class BallRoomActivity extends BaseListActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    private List<BallRoomBean> list;
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, BallRoomActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房大厅").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.include_ptr_recycler);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });
        for (int i=0;i<7;i++){
            BallRoomBean ballRoomBean = new BallRoomBean();
            list.add(ballRoomBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void initData() {
        mAdapter=new BallRoomAdapter();
        list=new ArrayList<>();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

}
