package com.yuyuka.billiards.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.ui.adapter.news.NewsCommentAdapter;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.dialog.CommentDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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

    CommentDialog dialog;
    int userId;
    boolean isAttention;
    @BindView(R.id.btn_attention)
    TextView attionBtn;
    @BindView(R.id.btn_attention1)
    TextView attionBtn1;


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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsCommentItem commentItem = (NewsCommentItem) adapter.getData().get(position);
                ArtivleReplyActivity.launcher(getContext(),commentItem);
            }
        });
    }

    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    public void showAttentionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = true;
        attionBtn.setBackgroundResource(R.drawable.bg_news_attention_gray);
        attionBtn.setText("已关注");
        attionBtn1.setBackgroundResource(R.drawable.bg_news_attention_gray);
        attionBtn1.setText("已关注");
    }

    @Override
    public void showAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showCommentSuccess(String msg) {
        ToastUtils.showToast(this,msg);
        dialog.dismiss();
        onRefresh();
    }

    @Override
    public void showCommentFailure(String msg) {
        ToastUtils.showToast(this,msg);
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
//        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
//                "<html>\n" +
//                "<head>\n" +
//                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-16\">\n" +
//                "<meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n" +
//                "<title></title>\n" +
//                "<meta name=\"Generator\" content=\"Cocoa HTML Writer\">\n" +
//                "<style type=\"text/css\">\n" +
//                "p.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 18.0px '.PingFang SC'; color: #8e8e93}\n" +
//                "p.p2 {margin: 0.0px 0.0px 0.0px 0.0px; font: 14.0px '.PingFang SC'; color: #000000}\n" +
//                "p.p3 {margin: 0.0px 0.0px 0.0px 0.0px; font: 14.0px '.PingFang SC'; color: #656565}\n" +
//                "p.p4 {margin: 0.0px 0.0px 0.0px 0.0px; font: 14.0px '.PingFang SC'; color: #656565; background-color: #aaaaaa}\n" +
//                "p.p5 {margin: 0.0px 0.0px 8.0px 20.0px; font: 14.0px '.SF UI Text'; color: #656565; min-height: 16.7px}\n" +
//                "p.p6 {margin: 0.0px 0.0px 0.0px 0.0px; font: 12.0px Helvetica; min-height: 13.8px}\n" +
//                "p.p7 {margin: 0.0px 0.0px 0.0px 0.0px; font: 12.0px Helvetica}\n" +
//                "p.p8 {margin: 0.0px 0.0px 30.0px 20.0px; font: 14.0px '.SF UI Text'; color: #656565; min-height: 16.7px}\n" +
//                "span.s1 {font-family: '.PingFangSC-Regular'; font-weight: normal; font-style: normal; font-size: 18.00pt}\n" +
//                "span.s2 {font-family: '.PingFangSC-Medium'; font-weight: bold; font-style: normal; font-size: 14.00pt}\n" +
//                "span.s3 {font-family: '.PingFangSC-Regular'; font-weight: normal; font-style: normal; font-size: 14.00pt; text-decoration: underline}\n" +
//                "span.s4 {font-family: '.PingFangSC-Regular'; font-weight: normal; font-style: normal; font-size: 14.00pt}\n" +
//                "span.s5 {font-family: '.SFUIText'; font-weight: normal; font-style: normal; font-size: 14.00pt}\n" +
//                "span.s6 {font-family: 'Helvetica'; font-weight: normal; font-style: normal; font-size: 12.00pt}\n" +
//                "</style>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<p class=\"p1\"><span class=\"s1\">这是标题</span></p >\n" +
//                "<p class=\"p2\"><span class=\"s2\">这是加粗</span></p >\n" +
//                "<p class=\"p3\"><span class=\"s3\">这是下划线</span></p >\n" +
//                "<p class=\"p4\"><span class=\"s4\">这是引用</span></p >\n" +
//                "<p class=\"p5\"><span class=\"s5\"></span><br></p >\n" +
//                "<p class=\"p3\"><span class=\"s5\">1</span><span class=\"s4\">、列表</span><span class=\"s5\">1</span></p >\n" +
//                "<p class=\"p3\"><span class=\"s5\">2</span><span class=\"s4\">、列表</span><span class=\"s5\">2</span></p >\n" +
//                "<p class=\"p6\"><span class=\"s6\"></span><br></p >\n" +
//                "<p class=\"p6\"><span class=\"s6\"></span><br></p >\n" +
//                "<p class=\"p7\"><span class=\"s6\"><img src=\"http://img4.imgtn.bdimg.com/it/u=2280945564,1168779340&fm=26&gp=0.jpg\" alt=\"http://cripple.oss-cn-beijing.aliyuncs.com/ce511fc9-b124-41c9-ac7f-109dcc8cc13f201909251247000.png?Expires=1884746820&OSSAccessKeyId=LTAIwIMSrFp3QBrH&Signature=RJMjneTSQdYc%2B48h89H%2Fcg5bQOw%3D\"/></span></p >\n" +
//                "<p class=\"p6\"><span class=\"s6\"></span><br></p >\n" +
//                "<p class=\"p8\"><span class=\"s5\"></span><br></p >\n" +
//                "<p class=\"p3\"><span class=\"s5\">•</span><span class=\"s4\">无序列表</span><span class=\"s5\">1</span></p >\n" +
//                "<p class=\"p3\"><span class=\"s5\">•</span><span class=\"s4\">无序列表</span><span class=\"s5\">2</span></p >\n" +
//                "</body>\n" +
//                "</html>";
        if (data!=null){
            mWebView.loadData(data.getContentInfo()+js,"text/html; charset=UTF-8", null);
            ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),headIv,new LoadOption().setIsCircle(true));
            ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),headIv1,new LoadOption().setIsCircle(true));
            userTv.setText(data.getBilliardsUsers().getUserName());
            userTv1.setText(data.getBilliardsUsers().getUserName());
            timeTv.setText(data.getCreated());
            titleTv.setText(data.getTitle());
            userId = data.getUserId();
        }


    }

    @Override
    public void showReplyList(List<NewsReplyItem> data) {

    }

    @Override
    public void showDisAttenttionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = false;
        attionBtn.setBackgroundResource(R.drawable.bg_news_attention_orange);
        attionBtn.setText("关注");
        ToastUtils.showToast(getContext(),msg);
        isAttention = false;
        attionBtn1.setBackgroundResource(R.drawable.bg_news_attention_orange);
        attionBtn1.setText("关注");
    }

    @Override
    public void showDisAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);

    }

    @OnClick({R.id.discuss_tv1,R.id.btn_comment,R.id.btn_attention1,R.id.btn_attention})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.discuss_tv1:
                dialog = new CommentDialog(this);
                dialog.setOnCommentListener(content -> {
                    getPresenter().comment(consultationId,content);
                });
                dialog.show();
                break;
            case R.id.btn_comment:
                smoothMoveToPosition(mRecyclerView,2);
                break;
            case R.id.btn_zan:
                getPresenter().appreciate(consultationId);
                break;
            case R.id.btn_attention:
                if (!isAttention)
                    getPresenter().attention(userId);
                else
                    getPresenter().disattention(userId);
                break;
            case R.id.btn_attention1:
                if (!isAttention)
                    getPresenter().attention(userId);
                else
                    getPresenter().disattention(userId);
                break;
        }
    }

    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
        }
    }


}
