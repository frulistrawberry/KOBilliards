package com.yuyuka.billiards.ui.adapter.news;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.activity.news.ArticleDetailActivity;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.SizeUtils;

import java.util.ArrayList;


public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsItem, BaseViewHolder> {

    public NewsAdapter() {
        super(new ArrayList<>());
        addItemType(0, R.layout.item_news_text);
        addItemType(1, R.layout.item_news_small_video);
        addItemType(2, R.layout.item_news_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsItem item) {
        NewsItem.BilliardsUsers user = item.getBilliardsUsers();
        helper.setText(R.id.tv_title,item.getTitle());
        switch (item.getItemType()){
            case 0:
                //文章
                if (TextUtils.isEmpty(item.getCoverImageAdd())){
                    helper.setGone(R.id.iv_cover,false);
                }else {
                    helper.setGone(R.id.iv_cover,false);
                    ImageManager.getInstance().loadNet(item.getCoverImageAdd(),helper.getView(R.id.iv_cover));
                }
                helper.setText(R.id.tv_time, DateUtils.converTime(item.getCreated()));
                if (user!=null)
                helper.setText(R.id.tv_user,user.getUserName());
                helper.getConvertView().setOnClickListener(v -> ArticleDetailActivity.launch(mContext,item.getId(),item.getContentInfo()));
                break;
            case 1:
                //小视频


                LinearLayout parent = helper.getView(R.id.ll_parent);
                if (helper.getAdapterPosition()%2 == 0){
                    parent.setPadding(0,0,SizeUtils.dp2px(mContext,2.5f),0);
                    parent.setGravity(Gravity.LEFT);
                }else {
                    parent.setPadding(SizeUtils.dp2px(mContext,2.5f),0,0,0);
                    parent.setGravity(Gravity.RIGHT);
                }

                ImageManager.getInstance().loadNet(item.getCoverImageAdd(),helper.getView(R.id.iv_cover));


                break;
            case 2:
                //视频
                helper.setText(R.id.tv_video_length,item.getViewLongtime()+"");
                ImageManager.getInstance().loadNet(item.getCoverImageAdd(),helper.getView(R.id.iv_cover));
                if (user!=null)
                helper.setText(R.id.tv_user,user.getUserName());

                break;
        }
    }
}
