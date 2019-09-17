package com.yuyuka.billiards.ui.activity.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.pojo.RankingBean;
import com.yuyuka.billiards.ui.adapter.ranking.RunkingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class RunkingActivity  extends BaseListActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.layout_ptr)
    PtrClassicFrameLayout layoutPtr;
    List<RankingBean> list;
    RankingBean rankingBean;
    public static void launcher(Context context) {
        Intent intent = new Intent(context, RunkingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("切换榜单").showBack().show();
    }
    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initData() {
       mAdapter=new RunkingAdapter();
       list=new ArrayList<>();
       mAdapter.setOnItemClickListener(this);
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
            rankingBean=new RankingBean();
            list.add(rankingBean);
        }
        mAdapter.setNewData(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
 //       recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position==0){
            ZhonshiBaqiuActivity.launcher(this);
        }else if (position==1){
            SiRuoActivity.launcher(this);
        }else if (position==2){
            RenqiActivity.launcher(this);
        } else if (position==3){
            ReputationActivity.launcher(this);
        } else  if (position==4){
            StreakActivity.launcher(this);
        }else  if (position==5){
            CanJuActivity.launcher(this);
        }else  if (position==6){
            DengJiActivity.launcher(this);
        }
    }
}
