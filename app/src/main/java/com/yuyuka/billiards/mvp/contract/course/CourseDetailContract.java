package com.yuyuka.billiards.mvp.contract.course;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.KOListPojo;

import java.util.List;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;

public interface CourseDetailContract {
    interface ICourseDetailView extends IBaseView {
        void showCourseInfo(KOListPojo data);

        void showCollectSuccess(String msg);
    }

    interface ICourseDetailModel extends IBaseModel {
        Observable<HttpResult> getCourseInfo(int id);

        Observable<HttpResult> collect(int merchantId);

        Observable<HttpResult> praise(int id);
    }

}
