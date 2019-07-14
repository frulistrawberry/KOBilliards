package com.yuyuka.billiards.base;


import com.google.gson.Gson;
import com.yuyuka.billiards.net.ApiEngine;
import com.yuyuka.billiards.net.ApiService;
import com.yuyuka.billiards.net.BizContent;

public class BaseModel implements IBaseModel {
    protected ApiService mService = ApiEngine.getInstance().getApiService();
    protected Gson mGson = new Gson();

    @Override
    public String convertBizContent(BizContent content) {
        return mGson.toJson(content);
    }
}
