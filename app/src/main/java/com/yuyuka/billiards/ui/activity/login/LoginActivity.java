package com.yuyuka.billiards.ui.activity.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.login.LoginContract;
import com.yuyuka.billiards.mvp.presenter.login.LoginPresenter;
import com.yuyuka.billiards.ui.activity.MainActivity;
import com.yuyuka.billiards.widget.StateButton;

import java.sql.Time;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.ILoginView {


    @BindView(R.id.btn_sms)
    StateButton mSmsBtn;
    @BindView(R.id.et_phone)
    EditText mPhoneEt;
    @BindView(R.id.et_sms)
    EditText mSmsEt;

    Timer timer;




    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setRightText("密码登录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).hideDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_login);
        timer = new Timer(60000,1000);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_sms,R.id.btn_login})
    public void onClick(View v){
        String phoneNum = mPhoneEt.getText().toString();
        String sms = mSmsEt.getText().toString();
        if (TextUtils.isEmpty(phoneNum)){
            showError("请输入手机号");
            return;
        }
        switch (v.getId()){
            case R.id.btn_sms:

                getPresenter().getSmsCode(phoneNum);
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(sms)){
                    showError("请输入验证码");
                    return;
                }
                getPresenter().login(phoneNum,sms);
                break;
        }
    }

    @Override
    public void showVerifyCodeSuccess() {
        timer.start();
    }

    @Override
    public void showVerifyCodeFailure(String errMsg) {
        showError(errMsg);
    }

    @Override
    public void showLoginSuccess() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void showLoginFailure(String msg) {
        showError(msg);
    }

    class Timer extends CountDownTimer {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mSmsBtn.setCanClick(false);
            long millis = millisUntilFinished / 1000;
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(millis).append("s)重发");
            mSmsBtn.setText(sb);
        }

        @Override
        public void onFinish() {
            mSmsBtn.setCanClick(true);
            mSmsBtn.setText("发送验证码");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null)
            timer.cancel();
    }
}
