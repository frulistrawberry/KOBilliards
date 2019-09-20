package com.yuyuka.billiards.ui.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.widget.StateButton;

import butterknife.BindView;
import butterknife.OnClick;

public class BlackActivity extends BaseActivity {
    @BindView(R.id.beizhu)
    LinearLayout beizhu;
    @BindView(R.id.switch_checkbox_message)
    CheckBox switchCheckboxMessage;
    @BindView(R.id.switch_checkbox_message1)
    CheckBox switchCheckboxMessage1;
    @BindView(R.id.switch_checkbox_message2)
    CheckBox switchCheckboxMessage2;
    @BindView(R.id.liaotian)
    StateButton liaotian;
    @BindView(R.id.delete)
    StateButton delete;

    public static void launcher(Context context) {
        context.startActivity(new Intent(context, BlackActivity.class));
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.layout_blackactivity);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.beizhu, R.id.liaotian, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.beizhu:
                BeizhuActivity.launcher(this);
                break;
            case R.id.liaotian:
                break;
            case R.id.delete:
                break;
        }
    }
}
