package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.widget.dialog.ReserveConfirmDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomReserveActivity extends BaseActivity {
    @BindView(R.id.ll_detail)
    LinearLayout mDetailLayout;

    public static void launcher(Context context){
        context.startActivity(new Intent(context, RoomReserveActivity.class));
    }


    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("预定").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reserve_detail);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_detail,R.id.btn_confirm,R.id.ll_detail})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_detail:
                if (mDetailLayout.getVisibility() == View.VISIBLE)
                    mDetailLayout.setVisibility(View.GONE);
                else
                    mDetailLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_detail:
                mDetailLayout.setVisibility(View.GONE);

                break;
            case R.id.btn_confirm:
                new ReserveConfirmDialog(this).show();
                break;
        }
    }
}
