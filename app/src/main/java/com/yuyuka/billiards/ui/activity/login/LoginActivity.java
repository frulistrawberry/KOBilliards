package com.yuyuka.billiards.ui.activity.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.login.LoginContract;
import com.yuyuka.billiards.mvp.presenter.login.LoginPresenter;
import com.yuyuka.billiards.ui.activity.MainActivity;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.StateButton;

import java.sql.Time;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.ILoginView, UMAuthListener {


    @BindView(R.id.btn_sms)
    StateButton mSmsBtn;
    @BindView(R.id.et_phone)
    EditText mPhoneEt;
    @BindView(R.id.et_sms)
    EditText mSmsEt;

    Timer timer;

    UMShareAPI umShareAPI;




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
        umShareAPI = UMShareAPI.get(this);
    }

    @OnClick({R.id.btn_sms,R.id.btn_login,R.id.btn_wx_login})
    public void onClick(View v){
        String phoneNum = mPhoneEt.getText().toString();
        String sms = mSmsEt.getText().toString();

        switch (v.getId()){
            case R.id.btn_sms:
                if (TextUtils.isEmpty(phoneNum)){
                    showError("请输入手机号");
                    return;
                }
                getPresenter().getSmsCode(phoneNum);
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(phoneNum)){
                    showError("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(sms)){
                    showError("请输入验证码");
                    return;
                }
                getPresenter().login(phoneNum,sms);
                break;
            case R.id.btn_wx_login:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, this);
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

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        LogUtil.e("umeng",share_media);
    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        LogUtil.e("umeng",new Gson().toJson(map));
        String name = map.get("name");
        String headImage = map.get("iconurl");
        String uid = map.get("uid");
        getPresenter().thirdLogin(name,uid,headImage,name,name,0);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        LogUtil.e("umeng",i);
        LogUtil.e("umeng",throwable);
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        LogUtil.e("umeng",i);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
