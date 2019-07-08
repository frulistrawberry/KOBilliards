package com.kobilliards.base;


import com.kobilliards.net.BizContent;

public class BaseModel implements IBaseModel {

    @Override
    public String convertBizContent(BizContent content) {
        return mGson.toJson(content);
    }
}
