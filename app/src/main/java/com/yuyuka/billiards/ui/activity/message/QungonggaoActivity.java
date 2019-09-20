package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

public class QungonggaoActivity extends BaseActivity {
    public static void launcher(Context context) {
        context.startActivity(new Intent(context, QungonggaoActivity.class));
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("群公告").setRightText("新建", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               QunGongGaoNewActivity.launcher(getContext());
            }
        }).showBack().show();
    }

    @Override
    protected void initView() {
      setContentView(R.layout.layout_qungonggaoactivity);
    }

    @Override
    protected void initData() {

    }
}
