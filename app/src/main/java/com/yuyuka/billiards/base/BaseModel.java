package com.yuyuka.billiards.base;


import com.google.gson.Gson;
import com.yuyuka.billiards.net.ApiEngine;
import com.yuyuka.billiards.net.ApiService;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.ProgressListener;
import com.yuyuka.billiards.net.UploadFileRequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BaseModel implements IBaseModel {
    protected ApiService mService = ApiEngine.getInstance().getApiService();
    protected Gson mGson = new Gson();

    @Override
    public String convertBizContent(BizContent content) {
        return mGson.toJson(content);
    }

    public Observable<HttpResult> upload(String imagePath, ProgressListener listener){
        File file = new File(imagePath);
        RequestBody requestFile = new UploadFileRequestBody(file,listener);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("mainImgFile", file.getName(), requestFile);

        return mService.upload(part);
    }
}
