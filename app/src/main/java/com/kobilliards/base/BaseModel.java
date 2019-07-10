package com.kobilliards.base;


import com.google.gson.Gson;
import com.kobilliards.net.ApiEngine;
import com.kobilliards.net.ApiService;
import com.kobilliards.net.BizContent;

public class BaseModel implements IBaseModel {
    protected ApiService mService = ApiEngine.getInstance().getApiService();
    protected Gson mGson = new Gson();

    @Override
    public String convertBizContent(BizContent content) {
        return mGson.toJson(content);
    }
}
