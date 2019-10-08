package com.yuyuka.billiards.ui.adapter.news;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;

import java.util.List;

public class NewsReplyAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public NewsReplyAdapter() {
        super(null);
        addItemType(0, R.layout.item_news_reply);
        addItemType(1,R.layout.item_news_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity data) {
        if (data.getItemType() == 0){
            NewsCommentItem item = (NewsCommentItem) data;
            ImageView headIv = helper.getView(R.id.iv_head);
            ImageManager.getInstance().loadNet(item.getBilliardsUsers().getHeadImage(),headIv,new LoadOption().setIsCircle(true));
            helper.setText(R.id.tv_user_name,item.getBilliardsUsers().getUserName());
//        helper.setText(R.id.tv_auth,item.getBilliardsUsers().getAuthentication());
            helper.setText(R.id.tv_content,item.getContent());
            helper.setText(R.id.tv_time,item.getCreated());
            helper.setGone(R.id.tv_reply_count,false);
            helper.setText(R.id.btn_attention,item.isAttention()?"已关注":"关注");
            helper.setOnClickListener(R.id.btn_attention, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    assert getOnItemChildClickListener() != null;
                    getOnItemChildClickListener().onItemChildClick(NewsReplyAdapter.this,helper.getView(R.id.btn_attention),helper.getAdapterPosition());
                }
            });
        }else if (data.getItemType() == 1){
            NewsReplyItem item = (NewsReplyItem) data;
            ImageView headIv = helper.getView(R.id.iv_head);
            ImageManager.getInstance().loadNet(item.getBilliardsUsers().getHeadImage(),headIv,new LoadOption().setIsCircle(true));
            helper.setText(R.id.tv_user_name,item.getBilliardsUsers().getUserName());
//        helper.setText(R.id.tv_auth,item.getBilliardsUsers().getAuthentication());
            helper.setText(R.id.tv_content,item.getReplyContent());
            helper.setText(R.id.tv_time,"");
            helper.setGone(R.id.tv_reply_count,false);

        }
    }
}
