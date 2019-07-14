package com.yuyuka.billiards.mvp.model.nearbyroom;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.rearbyroom.RecommendRoomContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;

import io.reactivex.Observable;

public class RecommendRoomModel extends BaseModel implements RecommendRoomContract.IRecommendRoomModel {
    @Override
    public Observable<HttpResult> getRecommendRoomList(int page, String sort, String filter) {
        BizContent content = new BizContent();
        content.mobile = "18631565231";
        RequestParam requestParam = new RequestParam(UrlConstant.GET_RECOMMEND_ROOM_LIST,convertBizContent(content));
        return mService.simpleRequest(requestParam);
//        return Observable.create(emitter -> {
//            Thread.sleep(3000);
//            List<BilliardsRoomPojo> bizEntity = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                bizEntity.add(new BilliardsRoomPojo());
//            }
//            HttpResult result = new HttpResult();
//            result.setCode(10000);
//            result.setMsg("请求成功");
//            result.setBizContent(new Gson().toJson(bizEntity));
//            result.setSign("");
//            emitter.onNext(result);
//            emitter.onComplete();
//        });
    }
}
