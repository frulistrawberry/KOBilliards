package com.yuyuka.billiards.mvp.model;

import com.google.gson.Gson;
import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.mvp.contract.goods.GoodsListContract;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.LivePojo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GoodsModel extends BaseModel implements GoodsListContract.IGoodsListModel {
    @Override
    public Observable<HttpResult> getGoodsList(int page) {
        return Observable.create(emitter -> {
            List<GoodsPojo> bizEntity = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bizEntity.add(new GoodsPojo());
            }
            HttpResult result = new HttpResult();
            result.setCode(10000);
            result.setMsg("请求成功");
            result.setBizContent(new Gson().toJson(new ListData<>(bizEntity)));
            result.setSign("");
            emitter.onNext(result);
            emitter.onComplete();
        });
    }
}
