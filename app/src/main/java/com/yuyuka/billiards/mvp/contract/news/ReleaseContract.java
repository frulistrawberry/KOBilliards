package com.yuyuka.billiards.mvp.contract.news;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.Tag;

import java.util.List;

import io.reactivex.Observable;

public interface ReleaseContract {
    interface IReleaseView extends IBaseView {
        void showReleaseSuccess(String msg);
        void showReleaseFailure(String msg);

        void showTags(List<Tag> tags);
    }

    interface IReleaseModel extends IBaseModel {
        Observable<HttpResult> releaseNews(int consultationType, int viewLongtime, String address, String coverImageAdd, String contentInfo, String title, String billiardsConsultationTagDtoIds);
        Observable<HttpResult> getTags();
    }
}
