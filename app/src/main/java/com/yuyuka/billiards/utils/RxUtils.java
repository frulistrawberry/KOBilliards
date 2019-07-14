package com.yuyuka.billiards.utils;


import com.yuyuka.billiards.base.IBaseView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    public static <T> ObservableTransformer<T, T> transform(final IBaseView view) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.bindToLifecycle());

    }


}
