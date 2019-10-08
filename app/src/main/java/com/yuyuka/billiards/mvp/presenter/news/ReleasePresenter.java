package com.yuyuka.billiards.mvp.presenter.news;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.mvp.contract.news.ReleaseContract;
import com.yuyuka.billiards.mvp.model.NewsModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.pojo.Tag;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.RxUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReleasePresenter extends BasePresenter<ReleaseContract.IReleaseView, ReleaseContract.IReleaseModel> {
    public ReleasePresenter(ReleaseContract.IReleaseView view) {
        super(view, new NewsModel());
    }

    public void releaseNews(int consultationType, int viewLongtime, String address, String coverImageAdd, String contentInfo, String title, String billiardsConsultationTagDtoIds) {
        getView().showProgressDialog();
        mModel.releaseNews(consultationType,viewLongtime,address,coverImageAdd,contentInfo,title,billiardsConsultationTagDtoIds)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();
                        getView().showReleaseSuccess(msg);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showReleaseFailure(errMsg);
                    }
                });
    }

    public void getTags() {
        getView().showProgressDialog();
        mModel.getTags()
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizContent) {
                        getView().dismissProgressDialog();

                        Type type = new TypeToken<List<Tag>>(){}.getType();
                        List<Tag> data = new Gson().fromJson(bizContent,type);
                        if (!CollectionUtils.isEmpty(data))
                            getView().showTags(data);

                    }
                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                    }
                });
    }
}
