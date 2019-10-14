package com.yuyuka.billiards.utils;


import com.yuyuka.billiards.constants.PreferenceConstant;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.yuyuka.billiards.pojo.UserInfo;

public class CommonUtils {

    public static LoginInfo getLoginInfo() {
        return isLogin()?new LoginInfo(String.valueOf(getUserId()),"123456"):null;
    }

    public static boolean isLogin(){
        return SPUtils.getBoolean(PreferenceConstant.IS_LOGIN,false);
    }

    public static Integer getUserId(){
        return SPUtils.getInt(PreferenceConstant.USER_ID);
    }

    public static String getToken(){
        return SPUtils.getString(PreferenceConstant.TOKEN);
    }

    public static void saveToken(String token){
        SPUtils.putString(PreferenceConstant.TOKEN,token);

    }

    public static void saveUserInfo(UserInfo userInfo){
        SPUtils.putInt(PreferenceConstant.USER_ID,22);
        SPUtils.putBoolean(PreferenceConstant.IS_LOGIN,true);
    }

    public static void saveLocationInfo(double lat,double lng){
        SPUtils.putFloat(PreferenceConstant.LATITUDE, (float) lat);
        SPUtils.putFloat(PreferenceConstant.LONGITUDE, (float) lng);
    }
}
