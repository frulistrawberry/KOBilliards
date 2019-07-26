package com.yuyuka.billiards.pojo;

import java.io.Serializable;
import java.util.List;

public class ListData<T> implements Serializable {
    private int pageSize;
    private int totalCount;
    private int currentPageNo;
    private List<T> dataList;

    public ListData(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public List<T> getDataList() {
        return dataList;
    }
}
