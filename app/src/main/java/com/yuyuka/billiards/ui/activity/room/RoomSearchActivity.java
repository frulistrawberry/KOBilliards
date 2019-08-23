package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.room.BilliardsRoomSearchContract;
import com.yuyuka.billiards.mvp.presenter.room.BilliardsRoomSearchPresenter;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.List;

import butterknife.BindView;

public class RoomSearchActivity extends BaseListActivity<BilliardsRoomSearchPresenter> implements BilliardsRoomSearchContract.IBilliardsRoomSearchView {

    @BindView(R.id.ll_default)
    LinearLayout mDefaultLayout;
    int style;
    public static void launcher(Context context,int titleStyle){
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra("titleStyle",titleStyle);

        context.startActivity(intent);
    }

    public static void launcher(Context context){
        Intent intent = new Intent(context, RoomSearchActivity.class);
        intent.putExtra("titleStyle",0);

        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        setTitleStyle(style);
        setContentView(R.layout.activity_billiards_room_search);
        mStatusBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }

    @Override
    protected void initData() {
        style = getIntent().getIntExtra("titleStyle",0);
    }

    @Override
    protected BilliardsRoomSearchPresenter getPresenter() {
        return new BilliardsRoomSearchPresenter(this);
    }

    @Override
    public void showBilliardsRoomList(List<BilliardsRoomPojo> roomList) {

    }
}
