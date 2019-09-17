package com.yuyuka.billiards.ui.activity.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.net.CanJuBean;
import com.yuyuka.billiards.ui.adapter.ranking.CanJuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ReputationActivity extends BaseListActivity {
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.v_title_divider)
    View vTitleDivider;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<CanJuBean> canJuBeanList;
    public static void launcher(Context context) {
        Intent intent = new Intent(context, ReputationActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {
        setContentView(R.layout.layout_reputationactivity);
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });
        for (int i=0;i<10;i++){
            CanJuBean canJuBean=new CanJuBean();
            canJuBeanList.add(canJuBean);
        }
        mAdapter.setNewData(canJuBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        mAdapter =new CanJuAdapter();
        canJuBeanList=new ArrayList<>();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
