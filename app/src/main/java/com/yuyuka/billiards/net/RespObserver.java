package com.yuyuka.billiards.net;

import com.yuyuka.billiards.utils.log.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class RespObserver implements Observer<HttpResult> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult httpResult) {
        LogUtil.json("bizContent",httpResult.getBizContent());
        if (httpResult.getCode()!=10000){
            onError(httpResult.getCode(),httpResult.getMsg());
        }else {
            onResult(httpResult.getMsg(),httpResult.getBizContent());
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("HttpError",e.getMessage());
         if (e instanceof Exception) {
            HttpThrowable httpThrowable = ThrowableHandler.handleThrowable(e);
            onError(httpThrowable.errorType,httpThrowable.message);
        } else {
            onError(HttpThrowable.UNKNOWN,"未知错误");
        }
        onComplete();
    }

    @Override
    public void onComplete() {

    }

    public abstract void onResult(String msg,String bizEntity);
    public abstract void onError(int errCode, String errMsg);

}
