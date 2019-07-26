package com.yuyuka.billiards.ui.adapter.live;

import android.view.Gravity;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.LivePojo;
import com.yuyuka.billiards.utils.SizeUtils;

public class LiveAdapter extends BaseQuickAdapter<LivePojo, BaseViewHolder> {
    public LiveAdapter() {
        super(R.layout.item_live);
    }

    @Override
    protected void convert(BaseViewHolder helper, LivePojo item) {
        LinearLayout parent = helper.getView(R.id.ll_parent);
        if (helper.getAdapterPosition()%2 == 0){
            parent.setPadding(SizeUtils.dp2px(mContext,8),0,SizeUtils.dp2px(mContext,2.5f),0);
            parent.setGravity(Gravity.LEFT);
        }else {
            parent.setPadding(SizeUtils.dp2px(mContext,2.5f),0,SizeUtils.dp2px(mContext,8),0);
            parent.setGravity(Gravity.RIGHT);
        }
    }
}
