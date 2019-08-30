package com.yuyuka.billiards.ui.activity.bonus;

import android.widget.EditText;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.widget.keyboard.BaseKeyboard;
import com.yuyuka.billiards.widget.keyboard.KeyboardManager;
import com.yuyuka.billiards.widget.keyboard.NumberKeyboard;

import butterknife.BindView;

public class RewardBonusActivity extends BaseActivity {

    @BindView(R.id.et_reward)
    EditText mRewardEt;

    private KeyboardManager keyboardManager;
    private NumberKeyboard numberKeyboard;

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("打赏").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_reward_bonus);
        keyboardManager = new KeyboardManager(this);
        numberKeyboard = new NumberKeyboard(this, NumberKeyboard.DEFAULT_NUMBER_XML_LAYOUT);
        numberKeyboard.setEnableDotInput(true);
        numberKeyboard.setKeyStyle(new BaseKeyboard.DefaultKeyStyle(this));
        numberKeyboard.setActionDoneClickListener(charSequence -> {

        });
        keyboardManager.bindToEditor(mRewardEt, numberKeyboard);
    }

    @Override
    protected void initData() {

    }
}
