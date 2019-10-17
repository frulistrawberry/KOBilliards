package com.yuyuka.billiards.base;


import com.yuyuka.billiards.net.ApiEngine;
import com.yuyuka.billiards.net.ApiService;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.ProgressListener;

import io.reactivex.Observable;

public interface IBaseModel {


    String convertBizContent(BizContent content);

    Observable<HttpResult> upload(String filePath, ProgressListener listener);

}
