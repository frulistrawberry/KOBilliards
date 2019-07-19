package com.yuyuka.billiards.utils;


import com.yuyuka.billiards.constants.PreferenceConstant;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class CommonUtils {

    public static LoginInfo getLoginInfo() {
        return isLogin()?new LoginInfo(getUserId(),getToken()):null;
    }

    public static boolean isLogin(){
        return SPUtils.getBoolean(PreferenceConstant.IS_LOGIN,false);
    }

    public static String getUserId(){
        return SPUtils.getString(PreferenceConstant.USER_ID);
    }

    public static String getToken(){
        return SPUtils.getString(PreferenceConstant.TOKEN);
    }

    public static void saveLocationInfo(double lat,double lng){
        SPUtils.putFloat(PreferenceConstant.LATITUDE, (float) lat);
        SPUtils.putFloat(PreferenceConstant.LONGITUDE, (float) lng);
    }
}
