package com.yuyuka.billiards.ui.adapter.goods;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.BilliardsUsers;
import com.yuyuka.billiards.pojo.GoodsComment;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.SizeUtils;

public class GoodsCommentAdapter extends BaseQuickAdapter<GoodsComment, BaseViewHolder> {
    public GoodsCommentAdapter() {
        super(R.layout.item_goods_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsComment item) {

        BilliardsUsers users = item.getBilliardsUsers();
        if (users!=null){
            helper.setText(R.id.tv_user_name,item.getBilliardsUsers().getUserName());
            ImageView imageView = helper.getView(R.id.iv_head);
            ImageManager.getInstance().loadNet(item.getBilliardsUsers().getHeadImage(),imageView,new LoadOption().setRoundRadius(SizeUtils.dp2px(mContext,4)));

        }
        helper.setText(R.id.tv_time, DateUtils.converTime(item.getCommentDate()));
        helper.setText(R.id.tv_content,item.getCommentContent());

    }
}
