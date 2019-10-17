package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.pojo.RoomStoreBean;
import com.yuyuka.billiards.pojo.firstStroeBean;
import com.yuyuka.billiards.ui.adapter.roomball.FirstStoreAdapter;
import com.yuyuka.billiards.ui.adapter.roomball.SecondStroeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RoomStoreActivity extends BaseActivity {
    @BindView(R.id.rv_recyclerview_one)
    RecyclerView recyclerView;
    @BindView(R.id.rv_recyclerview_two)
    RecyclerView recyclerViewTwo;
    @BindView(R.id.queren)
    Button queren;
    private FirstStoreAdapter firstStoreAdapter;
    private SecondStroeAdapter secondStroeAdapter;
    private List<firstStroeBean> firstStroeBeans;
    private List<RoomStoreBean> roomStoreBeanList;
    private List<RoomStoreBean> roomStoreBeanList1;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, RoomStoreActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房商铺").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_roomstoreactivity);
        for (int i = 0; i < 4; i++) {
            firstStroeBean firstStroeBean = new firstStroeBean();
            firstStroeBeans.add(firstStroeBean);
        }
        for (int i = 0; i < 5; i++) {
            RoomStoreBean roomStoreBean = new RoomStoreBean();
            roomStoreBeanList.add(roomStoreBean);
        }
        for (int i = 0; i < 8; i++) {
            RoomStoreBean roomStoreBean = new RoomStoreBean();
            roomStoreBeanList1.add(roomStoreBean);
        }
        firstStoreAdapter.setNewData(firstStroeBeans);
        secondStroeAdapter.setNewData(roomStoreBeanList);
        secondStroeAdapter.setNewData(roomStoreBeanList1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewTwo.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(firstStoreAdapter);
        recyclerViewTwo.setAdapter(secondStroeAdapter);
        firstStoreAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    secondStroeAdapter = new SecondStroeAdapter(roomStoreBeanList);
                    recyclerViewTwo.setAdapter(secondStroeAdapter);
                }
                if (position == 1) {
                    secondStroeAdapter = new SecondStroeAdapter(roomStoreBeanList1);
                    recyclerViewTwo.setAdapter(secondStroeAdapter);
                }

                if (position == 2) {
                    secondStroeAdapter = new SecondStroeAdapter(roomStoreBeanList);
                    recyclerViewTwo.setAdapter(secondStroeAdapter);
                }
                if (position == 3) {
                    secondStroeAdapter = new SecondStroeAdapter(roomStoreBeanList1);
                    recyclerViewTwo.setAdapter(secondStroeAdapter);
                }

            }
        });
    }

    @Override
    protected void initData() {
        firstStoreAdapter = new FirstStoreAdapter();
        secondStroeAdapter = new SecondStroeAdapter(roomStoreBeanList);
        firstStroeBeans = new ArrayList<>();
        roomStoreBeanList = new ArrayList<>();
        roomStoreBeanList1 = new ArrayList<>();
    }


    @OnClick(R.id.queren)
    public void onViewClicked() {
        PayActivity.launcher(this);
    }
}
