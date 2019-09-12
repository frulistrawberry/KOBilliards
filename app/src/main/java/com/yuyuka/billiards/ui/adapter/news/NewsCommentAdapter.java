package com.yuyuka.billiards.ui.adapter.news;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.NewsCommentItem;

public class NewsCommentAdapter extends BaseQuickAdapter<NewsCommentItem, BaseViewHolder> {
    public NewsCommentAdapter() {
        super(R.layout.item_news_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsCommentItem item) {
        ImageView headIv = helper.getView(R.id.iv_head);
        ImageManager.getInstance().loadNet(item.getBilliardsUsers().getHeadImage(),headIv,new LoadOption().setIsCircle(true));
        helper.setText(R.id.tv_user_name,item.getBilliardsUsers().getUserName());
//        helper.setText(R.id.tv_auth,item.getBilliardsUsers().getAuthentication());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_time,item.getCreated());
        helper.setGone(R.id.tv_reply_count,false);
    }
}
