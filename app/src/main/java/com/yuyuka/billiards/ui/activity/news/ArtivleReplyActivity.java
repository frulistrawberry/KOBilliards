package com.yuyuka.billiards.ui.activity.news;

import android.content.Context;
import android.content.Intent;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.ui.fragment.news.NewsReplyFragment;

public class ArtivleReplyActivity extends BaseActivity {
    NewsCommentItem item;

    public static void launcher(Context context,NewsCommentItem item){
        Intent intent = new Intent(context,ArtivleReplyActivity.class);
        intent.putExtra("commentItem",item);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_article_reply);
        NewsReplyFragment fragment = NewsReplyFragment.newFragment(item.getId(),item);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,fragment).commit();

    }

    @Override
    protected void initData() {
        item = (NewsCommentItem) getIntent().getSerializableExtra("commentItem");
    }
}
