package com.yuyuka.billiards.ui.adapter.goods;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.ui.activity.market.GoodsDetailActivity;
import com.yuyuka.billiards.utils.SizeUtils;

public class GoodsAdapter extends BaseQuickAdapter<GoodsPojo, BaseViewHolder> {
    public GoodsAdapter() {
        super(R.layout.item_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsPojo item) {
        LinearLayout parent = helper.getView(R.id.ll_parent);
        if (helper.getAdapterPosition()%2 == 0){
            parent.setPadding(SizeUtils.dp2px(mContext,8),0,SizeUtils.dp2px(mContext,2.5f),0);
            parent.setGravity(Gravity.LEFT);
        }else {
            parent.setPadding(SizeUtils.dp2px(mContext,2.5f),0,SizeUtils.dp2px(mContext,8),0);
            parent.setGravity(Gravity.RIGHT);
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
