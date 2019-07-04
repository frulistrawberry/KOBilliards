package com.kobilliards.net;

import com.kobilliards.utils.log.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class RespObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T data) {
        onResult(data);
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("HttpError",e.getMessage());
        if (e instanceof ResultException){
            onError(((ResultException) e).getCode(),e.getMessage());
            onErrorData(((ResultException) e).getCode(),e.getMessage(),((ResultException) e).getErrorData());
        } else if (e instanceof Exception) {
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

    public abstract void onResult(T data);
    public abstract void onError(int errCode, String errMsg);

    public void onErrorData(int errCode,String errMsg,String errorData){

    }
}
