package com.yuyuka.billiards.mvp.model.nearbymatch;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.mvp.contract.nearbymatch.RecommendMatchContract;
import com.yuyuka.billiards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RecommendMatchModel extends BaseModel implements RecommendMatchContract.IRecommendMatchModel {
    @Override
    public Observable<HttpResult> getRecommendMatchList(int page, String sort, String filter) {
//        BizContent content = new BizContent();
//        content.mobile = "18631565231";
//        RequestParam requestParam = new RequestParam(UrlConstant.GET_RECOMMEND_ROOM_LIST,convertBizContent(content));
//        return mService.simpleRequest(requestParam);
        return Observable.create(emitter -> {
            Thread.sleep(1000);
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
