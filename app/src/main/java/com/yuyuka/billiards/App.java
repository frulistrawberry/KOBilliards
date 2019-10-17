package com.yuyuka.billiards;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
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
import com.yuyuka.billiards.utils.AppUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.SPUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;

public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {



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
//        MixPushConfig config = new MixPushConfig();
//        config.xmAppId = Config.XM_APP_ID;
//        config.xmAppKey = Config.XM_APP_KEY;
//        config.xmCertificateName = Config.XM_CERTIFICATE_NAME;

//        options.mixPushConfig = config;
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,null);
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.init(this,"5d9759853fc195be0f001102","",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setWeixin(Config.WX_APP_ID, Config.WX_APP_SECRET);
        UMShareAPI.get(this);
        CommonUtils.saveUserInfo(null);
        NIMClient.init(this, CommonUtils.getLoginInfo(), options);
        if (NIMUtil.isMainProcess(this)){
            NIMClient.getService(AuthService.class).login(CommonUtils.getLoginInfo()).setCallback(new RequestCallback<LoginInfo>() {
                @Override
                public void onSuccess(LoginInfo param) {
//                    getView().showLoginSuccess();
//                    getView().dismissProgressDialog();
                }

                @Override
                public void onFailed(int code) {
//                    getView().showLoginFailure("IM登录失败,错误码"+code);
//                    getView().dismissProgressDialog();
                }

                @Override
                public void onException(Throwable exception) {

                }
            });


            NIMClient.getService(MsgServiceObserve.class).observeCustomNotification(new Observer<CustomNotification>() {
                @Override
                public void onEvent(CustomNotification message) {
                    // 在这里处理自定义通知。
                    LogUtil.e("IM",message.getContent());
                    EventBus.getDefault().post(message);
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
