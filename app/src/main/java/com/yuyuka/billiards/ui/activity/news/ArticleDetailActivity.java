package com.yuyuka.billiards.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsContentPresenter;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.adapter.news.NewsCommentAdapter;

import java.util.List;

public class ArticleDetailActivity extends BaseListActivity<NewsContentPresenter> implements NewsContract.INewsView {

    int consultationId;
    WebView mWebView;
    String newsContent;

    public static void launch(Context context,int consultationId,String newsContent){
        Intent intent = new Intent(context,ArticleDetailActivity.class);
        intent.putExtra("consultationId",consultationId);
        intent.putExtra("newsContent",newsContent);
        context.startActivity(intent);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_news_info);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWebView = new WebView(this);
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">"+
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        mWebView.loadData(newsContent+js,"text/html; charset=UTF-8", null);

        mAdapter.addHeaderView(mWebView);
        mAdapter.setHeaderAndEmpty(true);
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getNewsComment(consultationId,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage ++;
        getPresenter().getNewsComment(consultationId,mCurrentPage);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void initData() {
        mAdapter = new NewsCommentAdapter();
        consultationId = getIntent().getIntExtra("consultationId",0);
        newsContent = getIntent().getStringExtra("newsContent");
    }

    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    public void showAttentionSuccess(String msg) {

    }

    @Override
    public void showAttentionFailure(String msg) {

    }

    @Override
    public void showCommentSuccess(String msg) {

    }

    @Override
    public void showCommentFailure(String msg) {

    }

    @Override
    public void showPraiseSuccess(String msg) {

    }

    @Override
    public void showPraiseFailure(String msg) {

    }

    @Override
    public void showCommentList(List<NewsCommentItem> data) {
        if (mCurrentPage == 0)
            mAdapter.setNewData(data);
        else
            mAdapter.addData(data);
    }

    @Override
    public void showNewsInfo(NewsItem data) {

    }
}
