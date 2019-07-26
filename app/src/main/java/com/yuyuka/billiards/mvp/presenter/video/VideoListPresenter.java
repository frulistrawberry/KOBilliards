package com.yuyuka.billiards.mvp.presenter.video;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.video.VideoListContract;
import com.yuyuka.billiards.mvp.model.video.VideoListModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.VideoPojo;
import com.yuyuka.billiards.utils.CollectionUtils;

import java.lang.reflect.Type;

public class VideoListPresenter extends BasePresenter<VideoListContract.IVideoListView, VideoListContract.IVideoListModel> {
    public VideoListPresenter(VideoListContract.IVideoListView view) {
        super(view, new VideoListModel());
    }

    public void getVideoList(int page){
        getView().showLoading();
        mModel.getVideoList(page)
                .compose(getView().bindToLifecycle())
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        getView().hideLoading();
                        if (TextUtils.isEmpty(bizEntity)){
                            getView().showEmpty();
                            return;
                        }
                        Type type = new TypeToken<ListData<VideoPojo>>(){}.getType();
                        ListData<VideoPojo> data = new Gson().fromJson(bizEntity,type);

                        if (CollectionUtils.isEmpty(data.getDataList()))
                            getView().showEmpty();
                        else
                            getView().showVideoList(data.getDataList());
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().hideLoading();
                        getView().showError(errMsg);
                    }
                });
    }
}
