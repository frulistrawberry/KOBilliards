package com.kobilliards.base;


import com.kobilliards.net.ApiEngine;
import com.kobilliards.net.ApiService;

import io.reactivex.Observable;

public interface IBaseModel {
    ApiService mService = ApiEngine.getInstance().getApiService();



}
