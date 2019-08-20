package com.yuyuka.billiards.ui.adapter.news;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.utils.DateUtils;

import java.util.ArrayList;


public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsItem, BaseViewHolder> {

    public NewsAdapter() {
        super(new ArrayList<>());
        addItemType(0, R.layout.item_news_text);
        addItemType(2, R.layout.item_news_video);
        addItemType(1, R.layout.item_news_small_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsItem item) {
        NewsItem.BilliardsUsers user = item.getBilliardsUsers();
        helper.setText(R.id.tv_title,item.getTitle());
        ImageManager.getInstance().loadNet(item.getCoverImageAdd(),helper.getView(R.id.iv_cover));
        switch (item.getItemType()){
            case 0:
                //文章
                helper.setText(R.id.tv_time, DateUtils.converTime(item.getCreated()));
                helper.setText(R.id.tv_user,user.getUserName());
                break;
            case 2:
                //视频
                helper.setText(R.id.tv_video_length,item.getViewLongtime()+"");
                helper.setText(R.id.tv_user,user.getUserName());
                break;
            case 1:
                //小视频
                break;
        }
    }
}
