package com.yuyuka.billiards.ui.fragment.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListFragment;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.news.NewsContract;
import com.yuyuka.billiards.mvp.presenter.news.NewsContentPresenter;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.NewsItem;
import com.yuyuka.billiards.pojo.NewsReplyItem;
import com.yuyuka.billiards.ui.adapter.news.NewsCommentAdapter;
import com.yuyuka.billiards.ui.adapter.news.NewsReplyAdapter;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.ViewUtils;
import com.yuyuka.billiards.widget.dialog.CommentDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;

public class NewsReplyFragment extends BaseListFragment<NewsContentPresenter> implements NewsContract.INewsView {


    private int id;

    @BindView(R.id.iv_head_image_add_title)
    ImageView mHeadIv;
    @BindView(R.id.tv_user_name)
    TextView mUserNameTv;
    NewsCommentItem commentItem;
    @BindView(R.id.tv_fans_count)
    TextView mFansCountTv;
    @BindView(R.id.btn_attention)
    TextView mAttentionBtn;
    @BindView(R.id.tv_reply_count)
    TextView mReplyCountTv;

    CommentDialog mDialog;
    private boolean isAttention;


    public static NewsReplyFragment newFragment(int id, NewsCommentItem commentItem){
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putSerializable("commentItem",commentItem);
        NewsReplyFragment fragment = new NewsReplyFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 0;
        mPresenter.getReplyList(id,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        mPresenter.getReplyList(id,mCurrentPage);
    }

    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.news_reply_fragment,parent,false);
    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id",0);
        commentItem = (NewsCommentItem) getArguments().getSerializable("commentItem");
        mAdapter = new NewsReplyAdapter();

    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        ImageManager.getInstance().loadNet(commentItem.getBilliardsUsers().getHeadImage(),mHeadIv,new LoadOption().setIsCircle(true));
        mUserNameTv.setText(commentItem.getBilliardsUsers().getUserName());
        onRefresh();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!PtrDefaultHandler.canChildScrollUp(mRecyclerView)){
                    mReplyCountTv.setVisibility(View.VISIBLE);
                    mUserNameTv.setVisibility(View.GONE);
                    mFansCountTv.setVisibility(View.GONE);
                    mAttentionBtn.setVisibility(View.GONE);
                }else {
                    mReplyCountTv.setVisibility(View.GONE);
                    mUserNameTv.setVisibility(View.VISIBLE);
                    mFansCountTv.setVisibility(View.VISIBLE);
                    mAttentionBtn.setVisibility(View.VISIBLE);
                }
            }

        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.btn_attention){
                if (!isAttention)
                    getPresenter().attention(commentItem.getUserId());
                else
                    getPresenter().disattention(commentItem.getUserId());
            }
        });


    }



    @OnClick({R.id.iv_title_2,R.id.discuss_tv1,R.id.btn_zan,R.id.btn_attention})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_title_2:
                getActivity().onBackPressed();
                break;

            case R.id.discuss_tv1:
                mDialog = new CommentDialog(getContext());
                mDialog.setOnCommentListener(content -> {
                    getPresenter().reply(id,content);
                });
                mDialog.show();
                break;

            case R.id.btn_zan:
                getPresenter().appreciate(commentItem.getConsultationId());
                break;

            case R.id.btn_attention:
                if (!isAttention)
                    getPresenter().attention(commentItem.getUserId());
                else
                    getPresenter().disattention(commentItem.getUserId());
                break;
        }
    }

    @Override
    public void showAttentionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = true;
        commentItem.setAttention(isAttention);
        mAdapter.notifyItemChanged(0);
        mAttentionBtn.setBackgroundResource(R.drawable.bg_news_attention_gray);
        mAttentionBtn.setText("已关注");

    }

    @Override
    public void showAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showCommentSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        mDialog.dismiss();
        ToastUtils.showToast(getContext(),msg);
        onRefresh();
    }

    @Override
    public void showCommentFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showPraiseSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showPraiseFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }

    @Override
    public void showCommentList(List<NewsCommentItem> data) {

    }

    @Override
    public void showNewsInfo(NewsItem data) {

    }

    @Override
    public void showEmpty() {
        if (mAdapter == null)
            return;
        if (mCurrentPage == 0){
            List<MultiItemEntity> data = new ArrayList<>();
            data.add(commentItem);
            mAdapter.setNewData(data);
        }
        else if (isLoadMoreEnable()){
            mAdapter.loadMoreEnd(false);
            ToastUtils.showToast(getContext(),"已全部加载完成");
        }
    }

    @Override
    public void showReplyList(List<NewsReplyItem> data) {
        if (mCurrentPage == 0){
            List<MultiItemEntity> dataList = new ArrayList<>();
            dataList.add(commentItem);
            dataList.addAll(data);
            mAdapter.setNewData(dataList);
        }else
            mAdapter.addData(data);
    }

    @Override
    public void showDisAttenttionSuccess(String msg) {
        ToastUtils.showToast(getContext(),msg);
        isAttention = false;
        commentItem.setAttention(isAttention);
        mAdapter.notifyItemChanged(0);
        mAttentionBtn.setBackgroundResource(R.drawable.bg_news_attention_orange);
        mAttentionBtn.setText("关注");
    }

    @Override
    public void showDisAttentionFailure(String msg) {
        ToastUtils.showToast(getContext(),msg);
    }


}
