package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class ImagePojo implements Serializable {
    private String address = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564032893080&di=739a600f9607c85b4e774b16b840c254&imgtype=0&src=http%3A%2F%2Fpic.97uimg.com%2Fback_pic%2F20%2F15%2F11%2F03%2F0435894d9cb508cfa2083932b162202c.jpg";
    private String toAction;

    public String getImageUrl() {
        return address;
    }

    public String getToAction() {
        return toAction;
    }
}
