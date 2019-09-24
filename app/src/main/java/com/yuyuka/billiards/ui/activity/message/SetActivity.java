package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {
    @BindView(R.id.switch_checkbox_message)
    CheckBox switchCheckboxMessage;
    @BindView(R.id.switch_checkbox_message1)
    CheckBox switchCheckboxMessage1;
    @BindView(R.id.miandarao)
    RelativeLayout miandarao;
    @BindView(R.id.heimingdan)
    RelativeLayout heimingdan;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, SetActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("设置").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_setactivity);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.miandarao, R.id.heimingdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.miandarao:
                DisturbingActivity.launcher(this);
                break;
            case R.id.heimingdan:
                BlacklistActivity.launcher(this);
                break;
        }
    }
}
