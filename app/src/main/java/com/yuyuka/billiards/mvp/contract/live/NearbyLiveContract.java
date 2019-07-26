package com.yuyuka.billiards.mvp.contract.live;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.LivePojo;
import com.yuyuka.billiards.pojo.VideoPojo;

import java.util.List;

import io.reactivex.Observable;

public interface NearbyLiveContract {
    interface INearbyLiveView extends IBaseView {
        void showNearbyLiveList(List<LivePojo> dataList);
    }

    interface INearbyLiveModel extends IBaseModel{
        Observable<HttpResult> getNearbyLiveList(int page);
    }
}
