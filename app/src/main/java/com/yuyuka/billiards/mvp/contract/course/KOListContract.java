package com.yuyuka.billiards.mvp.contract.course;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.KOListPojo;

import java.util.List;

import io.reactivex.Observable;

public interface KOListContract  {
    interface IKOListView extends IBaseView {
        void showKOList(List<KOListPojo> data);
    }

    interface IKOListModel extends IBaseModel{
        Observable<HttpResult> getKOList(int id,int page);
    }
}
