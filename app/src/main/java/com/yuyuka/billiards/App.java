package com.yuyuka.billiards;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.kobilliards.BuildConfig;
import com.yuyuka.billiards.base.ActivityManager;
import com.yuyuka.billiards.constants.Config;
import com.yuyuka.billiards.constants.PreferenceConstant;
import com.yuyuka.billiards.utils.AppUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.SPUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {



    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        AppUtils.init(this);
        SPUtils.init(PreferenceConstant.PREFERENCE_NAME,this);
        LogUtil.init(BuildConfig.DEBUG);
        registerActivityLifecycleCallbacks(this);
//        SDKOptions options = new SDKOptions();
//        MixPushConfig config = new MixPushConfig();
//        config.xmAppId = Config.XM_APP_ID;
//        config.xmAppKey = Config.XM_APP_KEY;
//        config.xmCertificateName = Config.XM_CERTIFICATE_NAME;
//
//        options.mixPushConfig = config;
//        NIMClient.init(this, CommonUtils.getLoginInfo(), options);

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
