package com.yuyuka.billiards.ui.adapter.goods;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
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
        helper.getConvertView().setOnClickListener(v -> {
            GoodsDetailActivity.launch(mContext,Integer.valueOf(item.getId()));
        });
        helper.setText(R.id.tv_goods_name,item.getGoodsName());
        helper.setText(R.id.tv_user_name,item.getUserName());
        helper.setText(R.id.tv_parise_count,item.getPraiseCount()+"");
        helper.setText(R.id.tv_price,"￥"+item.getPrice());
        ImageView cover = helper.getView(R.id.iv_goods);
        ImageManager.getInstance().loadNet(item.getGoodsImages().replace("[","").replace("]",""),cover);
        ImageView user = helper.getView(R.id.iv_head);
        LoadOption option = new LoadOption().setIsCircle(true);
        ImageManager.getInstance().loadNet(item.getUserHeadImage(),user,option);



    }
}
