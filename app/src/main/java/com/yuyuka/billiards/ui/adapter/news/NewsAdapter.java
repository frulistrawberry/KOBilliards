package com.yuyuka.billiards.ui.adapter.news;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.ImageListener;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.activity.news.ArticleDetailActivity;
import com.yuyuka.billiards.ui.activity.news.SmallVideoDetailActivity;
import com.yuyuka.billiards.ui.activity.news.VideoDetailActivity;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsItem, BaseViewHolder> {
    String type;

    public NewsAdapter(String type) {
        super(new ArrayList<>());
        this.type = type;

        if (type.equals("news")){
            addItemType(0, R.layout.item_news_text);
            addItemType(1, R.layout.item_news_small_video);
            addItemType(2, R.layout.item_news_video);
        }else if (type.equals("home")){
            addItemType(0, R.layout.item_attention_text);
            addItemType(1, R.layout.item_home_small_video);
            addItemType(2, R.layout.item_home_video);
        }else if (type.equals("attention")){
            addItemType(0, R.layout.item_attention_text);
            addItemType(1, R.layout.item_news_small_video);
            addItemType(2, R.layout.item_attention_video);
        }else if (type.equals("recommend")){
            addItemType(0, R.layout.item_news_text);
            addItemType(1, R.layout.item_news_small_video);
            addItemType(2, R.layout.item_rec_video);
        }


    }

    @Override
    protected void convert(BaseViewHolder helper, NewsItem item) {
        NewsItem.BilliardsUsers user = item.getBilliardsUsers();
        helper.setText(R.id.tv_title,item.getTitle());
        ImageView imageView = helper.getView(R.id.iv_cover);
        switch (item.getItemType()){
            case 0:
                //文章

                if (type.equals("attention")){
                    ImageManager.getInstance().loadNet(item.getCoverImageAdd(),imageView,new LoadOption().setRoundRadius(SizeUtils.dp2px(mContext,4)));
                    helper.setText(R.id.tv_zan,item.getPraiseCount()+"");
                    helper.setText(R.id.tv_time, DateUtils.converTime(item.getCreated()));
                    if (user!=null){
                        helper.setText(R.id.tv_user,user.getUserName());
                        ImageView headIv = helper.getView(R.id.iv_head);
                        ImageManager.getInstance().loadNet(user.getHeadImage(),headIv,new LoadOption().setIsCircle(true));
                    }
                }else {
                    if (TextUtils.isEmpty(item.getConverImageAdd())){
                        helper.setGone(R.id.iv_cover,false);
                    }else {
                        helper.setGone(R.id.iv_cover,true);
                        ImageManager.getInstance().loadNet(item.getConverImageAdd(),imageView);
                    }
                    helper.setText(R.id.tv_time, DateUtils.converTime(item.getCreated()));
                    if (user!=null)
                        helper.setText(R.id.tv_user,user.getUserName());
                }


                helper.getConvertView().setOnClickListener(v -> ArticleDetailActivity.launch(mContext,item.getId(),item.getContentInfo()));
                break;
            case 1:
                //小视频
                ImageManager.getInstance().loadNet(item.getConverImageAdd(),imageView);
                helper.getConvertView().setOnClickListener(view -> SmallVideoDetailActivity.launcher(mContext,item.getId()));
                helper.setText(R.id.tv_zan,item.getPraiseCount()+"获赞");
                if (type.equals("home")){
                    helper.setText(R.id.tv_hot,item.getHotValue()+"");
                }
                if (type.equals("attention")){
                    ImageManager.getInstance().loadNet(item.getCoverImageAdd(),imageView);
                }
                break;
            case 2:
                //视频

                if (type.equals("news")){
                    helper.setText(R.id.tv_video_length,item.getViewLongtime()+"");
                    ImageManager.getInstance().loadNet(item.getConverImageAdd(),imageView);
                    if (user!=null){
                        helper.setText(R.id.tv_user,user.getUserName());
                        ImageView headIv = helper.getView(R.id.iv_head);
                        ImageManager.getInstance().loadNet(user.getHeadImage(),headIv,new LoadOption().setIsCircle(true));
                    }

                }else if (type.equals("home")){
                    helper.setText(R.id.tv_hot,item.getHotValue()+"");
                    helper.setText(R.id.tv_zan,item.getPraiseCount()+"获赞");
                    ImageManager.getInstance().loadNet(item.getConverImageAdd(),imageView);

                }else if (type.equals("recommend")){
                    helper.setText(R.id.tv_video_length,item.getViewLongtime()+"");
                    ImageManager.getInstance().loadNet(item.getConverImageAdd(),imageView);
                    if (user!=null){
                        helper.setText(R.id.tv_user,user.getUserName());
                        ImageView headIv = helper.getView(R.id.iv_head);
                        ImageManager.getInstance().loadNet(user.getHeadImage(),headIv,new LoadOption().setIsCircle(true));
                    }

                }else if (type.equals("attention")){
                    helper.setText(R.id.tv_video_length,item.getViewLongtime()+"");
                    ImageManager.getInstance().loadNet(item.getCoverImageAdd(),imageView);
                    helper.setText(R.id.tv_zan,item.getPraiseCount()+"");
                    helper.setText(R.id.tv_time, DateUtils.converTime(item.getCreated()));
                    if (user!=null){
                        helper.setText(R.id.tv_user,user.getUserName());
                        ImageView headIv = helper.getView(R.id.iv_head);
                        ImageManager.getInstance().loadNet(user.getHeadImage(),headIv,new LoadOption().setIsCircle(true));
                    }
                }
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VideoDetailActivity.launch(mContext,item.getId(),item.getAddress(),item.getCoverImageAdd());
                    }
                });

                break;
        }
    }
}
