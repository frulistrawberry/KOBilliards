package com.kobilliards;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.kobilliards.base.ActivityManager;
import com.kobilliards.constants.PreferenceConstant;
import com.kobilliards.utils.AppUtils;
import com.kobilliards.utils.CommonUtils;
import com.kobilliards.utils.SPUtils;
import com.kobilliards.utils.log.LogUtil;
import com.netease.nimlib.sdk.NIMClient;

public class App extends Application implements Application.ActivityLifecycleCallbacks {



    @Override
    public void onCreate() {
        super.onCreate();
        NIMClient.init(this, CommonUtils.getLoginInfo(), null);

        AppUtils.init(this);
        SPUtils.init(PreferenceConstant.PREFERENCE_NAME,this);
        LogUtil.init(BuildConfig.DEBUG);
        registerActivityLifecycleCallbacks(this);

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
