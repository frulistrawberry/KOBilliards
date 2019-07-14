package com.yuyuka.billiards.net;

import android.text.TextUtils;

import com.yuyuka.billiards.constants.Config;
import com.yuyuka.billiards.constants.PreferenceConstant;
import com.yuyuka.billiards.utils.AppUtils;
import com.yuyuka.billiards.utils.MD5Utils;
import com.yuyuka.billiards.utils.PhoneUtils;
import com.yuyuka.billiards.utils.SPUtils;

import java.io.Serializable;

public class RequestParam implements Serializable {
    private String channelCode = Config.CHANNEL_CODE;
    private String timestamp = String.valueOf(System.currentTimeMillis()/1000);
    private String authToken = SPUtils.getString(PreferenceConstant.TOKEN);
    private String deviceInfo = PhoneUtils.getDeviceId(AppUtils.getAppContext());
    private String bizContent;
    private String method;
    private String sign;

    public RequestParam(String method,String bizContent) {
        this.bizContent = bizContent;
        this.method = method;
        sign = sign();
    }

    private String sign() {
        StringBuilder sign = new StringBuilder(channelCode);
        if(!TextUtils.isEmpty(sign)){
            sign.append(bizContent);
        }
        sign.append(Config.SIGN_KEY);
        return MD5Utils.MD5(sign.toString());
    }
}
