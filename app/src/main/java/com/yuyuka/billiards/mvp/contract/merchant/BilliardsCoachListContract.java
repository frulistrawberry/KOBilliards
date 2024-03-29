package com.yuyuka.billiards.mvp.contract.merchant;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;

import java.util.List;

import io.reactivex.Observable;

public interface BilliardsCoachListContract {
    interface IBilliardsCoachListView extends IBaseView {
        void showBilliardsCoachList(List<BilliardsCoachPojo> coachList);
    }

    interface  IBilliardsCoachListModel extends IBaseModel{
        Observable<HttpResult> getBilliardsCoachList(int page);
    }
}
