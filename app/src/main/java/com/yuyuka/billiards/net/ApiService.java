package com.yuyuka.billiards.net;


import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST(UrlConstant.PATH)
    Observable<HttpResult> simpleRequest(@Body RequestParam body);

}

