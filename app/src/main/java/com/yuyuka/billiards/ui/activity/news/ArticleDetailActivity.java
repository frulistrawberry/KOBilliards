package com.yuyuka.billiards.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsContentPresenter;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.ui.adapter.news.NewsCommentAdapter;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ArticleDetailActivity extends BaseListActivity<NewsContentPresenter> implements NewsContract.INewsView {

    int consultationId;
    WebView mWebView;
    String newsContent;
    @BindView(R.id.app_bar)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.toolbar_open)
    RelativeLayout mToolbarOpenLayout;
    @BindView(R.id.toolbar_close)
    RelativeLayout mToolbarCloseLayout;
    String js = "<script type=\"text/javascript\">"+
            "var imgs = document.getElementsByTagName('img');" + // 找到img标签
            "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
            "imgs[i].style.width = '100%';" +  // 宽度改为100%
            "imgs[i].style.height = 'auto';" +
            "}" +
            "</script>";
    @BindView(R.id.iv_head_image_add_title)
    ImageView headIv;
    @BindView(R.id.iv_head_image_add)
    ImageView headIv1;
    @BindView(R.id.tv_user)
    TextView userTv;
    @BindView(R.id.tv_user_name)
    TextView userTv1;
    @BindView(R.id.tv_time)
    TextView timeTv;
    @BindView(R.id.tv_title1)
    TextView titleTv;


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

        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                LogUtil.d("onStateChanged",state);
                if (state == State.COLLAPSED){
                    mToolbarCloseLayout.setVisibility(View.VISIBLE);
                    mToolbarOpenLayout.setVisibility(View.GONE);
                }else {
                    mToolbarOpenLayout.setVisibility(View.VISIBLE);
                    mToolbarCloseLayout.setVisibility(View.GONE);
                }



            }
        });
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }
        });


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWebView = new WebView(this);
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS


        mAdapter.addHeaderView(mWebView);
        mAdapter.setHeaderAndEmpty(true);
        mRecyclerView.setAdapter(mAdapter);

        onRefresh();
    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getNewsComment(consultationId,mCurrentPage);
        getPresenter().getNewsInfo(consultationId);
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
        mWebView.loadData(data.getContentInfo()+js,"text/html; charset=UTF-8", null);
        ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),headIv,new LoadOption().setIsCircle(true));
        ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),headIv1,new LoadOption().setIsCircle(true));
        userTv.setText(data.getBilliardsUsers().getUserName());
        userTv1.setText(data.getBilliardsUsers().getUserName());
        timeTv.setText(data.getCreated());
        titleTv.setText(data.getTitle());

    }
}
