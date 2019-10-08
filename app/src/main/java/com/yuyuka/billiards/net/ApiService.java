package com.yuyuka.billiards.net;


import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiService {

    @POST(UrlConstant.PATH)
    Observable<HttpResult> simpleRequest(@Body RequestParam body);

    @Multipart
    @POST(UrlConstant.UP_LOAD_PATH)
    Observable<HttpResult> upload(@Part MultipartBody.Part imgs);

}

