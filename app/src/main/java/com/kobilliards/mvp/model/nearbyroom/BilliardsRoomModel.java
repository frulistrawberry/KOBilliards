package com.kobilliards.mvp.model.nearbyroom;

import com.google.gson.Gson;
import com.kobilliards.base.BaseModel;
import com.kobilliards.mvp.contract.rearbyroom.BilliardsRoomListContract;
import com.kobilliards.mvp.contract.rearbyroom.CollectionRoomContract;
import com.kobilliards.net.HttpResult;
import com.kobilliards.pojo.BilliardsRoomPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class BilliardsRoomModel extends BaseModel implements BilliardsRoomListContract.IBilliardsRoomListModel {
    @Override
    public Observable<HttpResult> getBilliardsRoomList(int page) {
//        BizContent content = new BizContent();
//        content.page = page;
//        return mService.simpleRequest(UrlConstant.GET_RECOMMEND_ROOM_LIST,convertBizContent(content));
        return Observable.create(emitter -> {
            Thread.sleep(3000);
            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new BilliardsRoomPojo());
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
