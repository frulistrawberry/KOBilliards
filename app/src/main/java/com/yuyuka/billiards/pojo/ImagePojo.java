package com.yuyuka.billiards.pojo;

import android.text.TextUtils;

import java.io.Serializable;

public class ImagePojo implements Serializable {
    private String address = "http://cripple.oss-cn-beijing.aliyuncs.com//dbf45faf-054c-4245-b0ea-9d54237b8bff201909271632002.jpg?Expires=1884933122&OSSAccessKeyId=LTAIwIMSrFp3QBrH&Signature=%2Butp3DEjiwhAgl3lJryUAuoxjTY%3D";
    private String toAction;

    public ImagePojo(String address) {
        if (!TextUtils.isEmpty(address))
        this.address = address;
    }

    public String getImageUrl() {
        return address;
    }

    public String getToAction() {
        return toAction;
    }
}
