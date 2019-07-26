package com.yuyuka.billiards.mvp.contract.video;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.VideoPojo;

import java.util.List;

import io.reactivex.Observable;

public interface VideoListContract {
    interface IVideoListView extends IBaseView{
        void showVideoList(List<VideoPojo> dataList);
    }

    interface  IVideoListModel extends IBaseModel{
        Observable<HttpResult> getVideoList(int page);
    }
}
