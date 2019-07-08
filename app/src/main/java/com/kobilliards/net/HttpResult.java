package com.kobilliards.net;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HttpResult {
    private int code;
    private String msg;
    private String bizContent;
    private String sign;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getBizContent() {
        return bizContent;
    }

    public String getSign() {
        return sign;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
