package com.yuyuka.billiards;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.mixpush.MixPushConfig;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yuyuka.billiards.base.ActivityManager;
import com.yuyuka.billiards.constants.Config;
import com.yuyuka.billiards.constants.PreferenceConstant;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.ui.activity.login.LoginActivity;
import com.yuyuka.billiards.utils.AppUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.SPUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;

public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {



    {
        PlatformConfig.setWeixin(Config.WX_APP_ID, Config.WX_APP_SECRET);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        AppUtils.init(this);
        SPUtils.init(PreferenceConstant.PREFERENCE_NAME,this);
        LogUtil.init(BuildConfig.DEBUG);
        registerActivityLifecycleCallbacks(this);
        ZXingLibrary.initDisplayOpinion(this);
        SDKOptions options = new SDKOptions();
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.init(this,"5dabc0cc0cafb22976000d1b","",UMConfigure.DEVICE_TYPE_PHONE,"");

        UMShareAPI.get(this);
        NIMClient.init(this, CommonUtils.getLoginInfo(), options);

        if (NIMUtil.isMainProcess(this)){
            NimUIKit.init(this);
            NIMClient.getService(MsgServiceObserve.class).observeCustomNotification((Observer<CustomNotification>) message -> {
                EventBus.getDefault().post(message);
            }, true);

            NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(
                    (Observer<StatusCode>) status -> {
                        if (status.wontAutoLogin()) {
                            // 被踢出、账号被禁用、密码错误等情况，自动登录失败，需要返回到登录界面进行重新登录操作
                            CommonUtils.clearUserInfo();
                            startActivity(new Intent(ActivityManager.getInstance().getCurrentActivity(), LoginActivity.class));
                        }
                    }, true);
        }

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManager.getInstance().setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
