package com.yuyuka.billiards.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.facetoface.BattleWaitActivity;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.BattleEndActivity;
import com.yuyuka.billiards.ui.activity.table.BattleResultActivity;
import com.yuyuka.billiards.ui.activity.table.ConfirmPointActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.TitleBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yuyuka.billiards.widget.dialog.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity {


    protected static final int REQUEST_CODE_SETTING = 106;

    private ProgressDialog mProgressDialog;
    private OnKeyClickListener mOnKeyClickListener;
    protected ViewGroup mRoot;
    private TitleBar mTitleBar;
    protected View mStatusBar;
    private int titleStyle;

    protected boolean fullScreen = true;

    protected boolean isCustom = false;

    protected boolean isBackground;

    /**
     * 按键的监听，供页面设置自定义的按键行为
     */
    public interface OnKeyClickListener {
        /**
         * 点击了返回键
         */
        void clickBack();
    }


    public Context getContext() {
        return this;
    }
    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);

    }
    public String getResourceString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }
    public String getResourceString(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }
    public void setOnKeyClickListener(OnKeyClickListener onkeyClickListener){
        mOnKeyClickListener = onkeyClickListener;
    }

    public TitleBar getTitleBar() {
        return mTitleBar;
    }

    public View getStatusbar(){
        return mStatusBar;
    }

    public void showProgressDialog(){
        if (mProgressDialog!=null && !mProgressDialog.isShowing())
            mProgressDialog.show();
    }
    public void dismissProgressDialog(){
        if (mProgressDialog == null)
            return;
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (mProgressDialog != null&&mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mOnKeyClickListener != null) {
                    mOnKeyClickListener.clickBack();
                } else {
                    onBackPressed();
                }
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        final View content = getLayoutInflater().inflate(layoutResID,null);
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(LinearLayout.
                LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        content.setLayoutParams(params);
        setContentView(content);
        overridePendingTransition(R.anim.right_in, R.anim.alpha_out);
    }

    @Override
    public void setContentView(View contentView){
        mRoot = genRootView();
        mStatusBar = new View(this);
        mStatusBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));

        if (titleStyle == 0){
            mStatusBar.setBackgroundColor(getResourceColor(R.color.bg_status_bar));
        }else if (titleStyle == 1){
            mStatusBar.setBackgroundColor(getResourceColor(R.color.bg_status_bar_white));
        }else if (titleStyle == 2){
            mStatusBar.setBackgroundColor(getResourceColor(R.color.bg_title_bar_gold));
        }else if (titleStyle == 3){
            mStatusBar.setVisibility(View.GONE);
        }
        mTitleBar = new TitleBar(this,titleStyle);
        mTitleBar.hide();
        if (isCustom){

        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                mRoot.addView(mStatusBar);
            }
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP && fullScreen){
                Window window = getWindow();
                //下面这一行呢是android4.0起推荐大家使用的将布局延伸到状态栏的代码，配合5.0的设置状态栏颜色可谓天作之合
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                mRoot.addView(mStatusBar);
            }
        }

        mRoot.addView(mTitleBar);
        mRoot.addView(contentView);
        super.setContentView(mRoot);
        ButterKnife.bind(this);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.alpha_in,R.anim.right_out);
    }






    protected void initTitle(){

    }

    protected abstract void initView();

    protected abstract void initData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog.Builder(this).setMessage("拼命加载中...").setCancelable(true).build();
        EventBus.getDefault().register(this);
        initData();
        initView();
        initTitle();
    }

    private ViewGroup genRootView() {
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.
                LayoutParams(LinearLayout.
                LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    public void setTitleStyle(int titleStyle) {
        this.titleStyle = titleStyle;
    }

    @Subscribe
    public void onEvent(CustomNotification notification){
        if (!isBackground){
            dismissProgressDialog();
            LogUtil.json("onReceiveMsg",notification.getContent());
            CustomNoticePojo data = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
            if (data.getNoticeType() == 0){
                CustomNoticePojo.BizContent bizContent = data.getBizContent();
                CustomNoticePojo.Battle battle = bizContent.getBattle();
                if (battle.getBattleType() == CompetitionType.SCAN_BATTLE
                        ||battle.getBattleType() == CompetitionType.SCAN_RANK
                        ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_BATTLE
                        ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK){
                    long tableNum = bizContent.getTableNum();
                    BattleWaitActivity.launcher(this, tableNum,battle.getId(),battle.getRefOrderId(),battle.getBattleType());
                }
            }
            else if (data.getNoticeType() == 1){
                //比赛开始进入对战页面
                CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
                if (battle.getBattleType() == CompetitionType.SCAN_BATTLE
                        ||battle.getBattleType() == CompetitionType.SCAN_RANK
                        ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_BATTLE
                        ||battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK) {
                    BattleActivity.launcher(this,data);

                }else if (battle.getBattleType() == CompetitionType.OPEN_TABLE){
                    SingleBattleActivity.launcher(this,data);
                }
            }else if (data.getNoticeType() == 2){
                BattleEndActivity.launcher(this,data);
            }else if (data.getNoticeType() == 3){
                ConfirmPointActivity.launch(this,data);
            } else if (data.getNoticeType() == 4){
                CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
                int myPoint = 0;
                int otherPoint = 0;
                if (CommonUtils.getUserId() == battle.getUserId1()){
                    myPoint = battle.getUser1Point();
                    otherPoint = battle.getUser2Point();
                }else {
                    myPoint = battle.getUser2Point();
                    otherPoint = battle.getUser1Point();
                }
                BattleResultActivity.launcher(this,data,myPoint>otherPoint);
            } else if (data.getNoticeType() == 5){
                CustomNoticePojo.MakeOrderInfo info = data.getBizContent().getMakeOrderInfo();
                ToastUtils.showToast(this,"成功退款"+info.getPayRefundAmount()+"元");
            } else if (data.getNoticeType() == 7){
                TablePayActivity.launcher(this,data);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBackground = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isBackground = true;
    }
}
