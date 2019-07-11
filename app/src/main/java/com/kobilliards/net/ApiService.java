package com.kobilliards.net;


import com.kobilliards.constants.UrlConstant;
import com.kobilliards.pojo.BilliardsRoomPojo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST(UrlConstant.PATH)
    Observable<HttpResult> simpleRequest(@Body RequestParam body);

}

