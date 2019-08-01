package com.yuyuka.billiards.utils;

import android.text.TextUtils;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.yuyuka.billiards.constants.PreferenceConstant;

import java.math.BigDecimal;

public class DataOptionUtils {

    public static String getEncryptMobile(String mobile) {
        if (mobile == null) {
            return "";
        }
        String encryptMobile;
        if (mobile.length() > 10) {
            encryptMobile = mobile.substring(0, 3) + " **** " + mobile.substring(7, 11);
        } else {
            encryptMobile = "";
        }
        return encryptMobile;
    }

    /**
     * 隐藏中间的位数
     *
     * @param str
     * @return
     */
    public static StringBuilder getHideCenterString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(str)){
            return stringBuilder;
        }
        for (int i = 0; i < str.length(); i++) {
            if (i <= 3) {
                stringBuilder.append(str.charAt(i));
            } else if (i < str.length() - 4 && str.length() > 3) {
                stringBuilder.append("*");
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder;
    }

    /**
     *  四舍五入方式保留两位小数
     * 例如：300.905 -->300.91
     */

    public static String getStringWithRound(String money){
        if(TextUtils.isEmpty(money)){
            return "";
        }
        BigDecimal bd = new BigDecimal(String.valueOf(money));
        double  d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.format("%.2f", d);

    }

    /**
     * 计算直线距离
     * @param lat
     * @param lng
     * @return
     */
    public static String calculateLineDistance(double lat,double lng){
        String distance = "";
        float latitude = SPUtils.getFloat(PreferenceConstant.LATITUDE);
        float longitude = SPUtils.getFloat(PreferenceConstant.LONGITUDE);
        if (latitude != -1 && longitude !=-1){
            LatLng latLng1 = new LatLng(lat,lng);
            LatLng latLng2 = new LatLng(latitude,longitude);
            float lineDistance = AMapUtils.calculateLineDistance(latLng1,latLng2);
            if (lineDistance > 1000){
                distance = getStringWithRound(String.valueOf(lineDistance / 1000)) + "km";
            }else {
                distance = getStringWithRound(String.valueOf(lineDistance)) + "m";
            }
        }

        return distance;
    }


    public static int getGoodsSortParam(String str){
        switch (str){
            case "价格从高到低":
                return 1;
            case "价格从低到高":
                return 2;
            case "最新发布":
                return 3;
            case "离我最近":
                return 4;
                default:
                    return 0;
        }
    }

}
