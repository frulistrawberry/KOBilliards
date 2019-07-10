package com.kobilliards.mvp.contract.rearbyroom;

import com.kobilliards.base.IBaseModel;
import com.kobilliards.base.IBaseView;
import com.kobilliards.net.HttpResult;
import com.kobilliards.pojo.BilliardsCoachPojo;

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
