package com.yuyuka.billiards.mvp.contract.ko;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.KOClassPojo;
import com.yuyuka.billiards.ui.activity.ko.KOClassActivity;

import java.util.List;

import io.reactivex.Observable;

public interface KOTypeContract {
    interface IKOTypeView extends IBaseView{
        void showKOClassList(List<KOClassPojo> dataList);
    }

    interface IKOTypeModel extends IBaseModel{
        Observable<HttpResult> getKOClassList(int modeType);
    }
}
