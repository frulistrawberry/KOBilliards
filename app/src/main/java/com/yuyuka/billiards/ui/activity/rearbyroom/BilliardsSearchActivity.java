package com.yuyuka.billiards.ui.activity.rearbyroom;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.rearbyroom.BilliardsRoomSearchContract;
import com.yuyuka.billiards.mvp.presenter.nearbyroom.BilliardsRoomSearchPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.List;

import butterknife.BindView;

public class BilliardsSearchActivity extends BaseListActivity<BilliardsRoomSearchPresenter> implements BilliardsRoomSearchContract.IBilliardsRoomSearchView {

    @BindView(R.id.ll_default)
    LinearLayout mDefaultLayout;

    public static void launcher(Context context){
        Intent intent = new Intent(context,BilliardsSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_room_search);
        mStatusBar.setVisibility(View.VISIBLE);

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

    }

    @Override
    protected BilliardsRoomSearchPresenter getPresenter() {
        return new BilliardsRoomSearchPresenter(this);
    }

    @Override
    public void showBilliardsRoomList(List<BilliardsRoomPojo> roomList) {

    }
}
