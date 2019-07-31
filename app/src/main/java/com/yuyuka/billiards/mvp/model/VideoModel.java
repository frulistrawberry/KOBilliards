package com.yuyuka.billiards.mvp.model;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.mvp.contract.live.NearbyLiveContract;
import com.yuyuka.billiards.mvp.contract.video.VideoListContract;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.LivePojo;
import com.yuyuka.billiards.pojo.VideoPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class VideoModel extends BaseModel implements VideoListContract.IVideoListModel {
    @Override
    public Observable<HttpResult> getVideoList(int page) {
        return Observable.create(emitter -> {
            List<VideoPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new VideoPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(new ListData<>(bizEntity)));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }
}
