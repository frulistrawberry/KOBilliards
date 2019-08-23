package com.yuyuka.billiards.ui.adapter.news;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.NewsCommentItem;

public class NewsCommentAdapter extends BaseQuickAdapter<NewsCommentItem, BaseViewHolder> {
    public NewsCommentAdapter() {
        super(R.layout.item_news_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsCommentItem item) {

    }
}
