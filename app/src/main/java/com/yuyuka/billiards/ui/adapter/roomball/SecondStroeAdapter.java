package com.yuyuka.billiards.ui.adapter.roomball;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.RoomStoreBean;

import java.util.List;

public class SecondStroeAdapter  extends BaseQuickAdapter<RoomStoreBean, BaseViewHolder> {
    public SecondStroeAdapter( @Nullable List<RoomStoreBean> data) {
        super(R.layout.layout_roomstoreitem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomStoreBean item) {

    }
}
