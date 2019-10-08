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
import com.yuyuka.billiards.widget.dialog.CommentDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;

public class SmallVideoCommentFragment extends BaseListFragment<NewsContentPresenter> implements NewsContract.INewsView {


    private int id;
    private int type;


    @BindView(R.id.tv_reply_count)
    TextView mReplyCountTv;

    CommentDialog mDialog;


    public static SmallVideoCommentFragment newFragment(int id,int type){
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putInt("type",type);
        SmallVideoCommentFragment fragment = new SmallVideoCommentFragment();
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
        if (type == 1)
            mPresenter.getReplyList(id,mCurrentPage);
        else
            mPresenter.getNewsComment(id,mCurrentPage);

    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        if (type == 1)
            mPresenter.getReplyList(id,mCurrentPage);
        else
            mPresenter.getNewsComment(id,mCurrentPage);
    }

    @Override
    protected NewsContentPresenter getPresenter() {
        return new NewsContentPresenter(this);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_small_video_comment,parent,false);
    }

    @Override
    protected void initData() {
        id = getArguments().getInt("id",0);
        type = getArguments().getInt("type",0);
        if (type == 1)
            mAdapter = new NewsReplyAdapter();
        else
            mAdapter = new NewsCommentAdapter();

        if (type == 0)
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NewsCommentItem item = (NewsCommentItem) adapter.getData().get(position);
            EventBus.getDefault().post(item);

        });


    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();

    }



    @OnClick({R.id.iv_title_2,R.id.discuss_tv1})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_title_2:
                getActivity().onBackPressed();
                break;

            case R.id.discuss_tv1:
                mDialog = new CommentDialog(getContext());
                mDialog.setOnCommentListener(content -> {
                    if (type ==1)
                        getPresenter().reply(id,content);
                    else
                        getPresenter().comment(id,content);
                });
                mDialog.show();
                break;
        }
    }

    @Override
    public void showAttentionSuccess(String msg) {


    }

    @Override
    public void showAttentionFailure(String msg) {
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
        if (mCurrentPage == 0){
            mAdapter.setNewData(data);
        }else
            mAdapter.addData(data);
    }

    @Override
    public void showNewsInfo(NewsItem data) {

    }


    @Override
    public void showReplyList(List<NewsReplyItem> data) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(data);
        }else
            mAdapter.addData(data);
    }

    @Override
    public void showDisAttenttionSuccess(String msg) {

    }

    @Override
    public void showDisAttentionFailure(String msg) {
    }


}
