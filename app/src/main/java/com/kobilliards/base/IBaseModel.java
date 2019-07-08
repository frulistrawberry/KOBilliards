package com.kobilliards.base;


import com.google.gson.Gson;
import com.kobilliards.net.ApiEngine;
import com.kobilliards.net.ApiService;
import com.kobilliards.net.BizContent;

import io.reactivex.Observable;

public interface IBaseModel {
    ApiService mService = ApiEngine.getInstance().getApiService();
    Gson mGson = new Gson();

    String convertBizContent(BizContent content);

}
