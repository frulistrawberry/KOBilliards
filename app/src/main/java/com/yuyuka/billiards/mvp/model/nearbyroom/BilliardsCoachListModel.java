package com.yuyuka.billiards.mvp.model.nearbyroom;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.mvp.contract.rearbyroom.BilliardsCoachListContract;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsCoachPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class BilliardsCoachListModel extends BaseModel implements BilliardsCoachListContract.IBilliardsCoachListModel {
    @Override
    public Observable<HttpResult> getBilliardsCoachList(int page) {
        return Observable.create(emitter -> {
            Thread.sleep(1000);
            List<BilliardsCoachPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsCoachPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(bizEntity));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }


}
