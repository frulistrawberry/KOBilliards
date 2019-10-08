package com.yuyuka.billiards.pojo;

public class UploadResult implements Comparable<UploadResult>{
    String path;
    int index;

    public UploadResult(String url, int index) {
        this.path = url;
        this.index = index;
    }

    public String getUrl() {
        return path;
    }

    public void setUrl(String url) {
        this.path = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(UploadResult uploadResult) {
        return index-uploadResult.index;
    }
}
