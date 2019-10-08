package com.yuyuka.billiards.ui.activity.market;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseListActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.market.GoodsDetailContract;
import com.yuyuka.billiards.mvp.presenter.market.GoodsDetailPresenter;
import com.yuyuka.billiards.pojo.GoodsComment;
import com.yuyuka.billiards.pojo.GoodsPojo;
import com.yuyuka.billiards.ui.adapter.goods.GoodsCommentAdapter;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.ViewUtils;
import com.yuyuka.billiards.widget.dialog.CommentDialog;

import java.util.List;

import butterknife.OnClick;

public class GoodsDetailActivity extends BaseListActivity<GoodsDetailPresenter> implements GoodsDetailContract.IGoodsDetailView {


    private int id;

    ImageView mHeadIv;
    TextView mUserTv;
    TextView mTagTv;
    TextView mUserDescTv;
    TextView mGoodsDescTv;
    TextView mGoodsPriceTv;
    TextView mGoodsZanTv;
    TextView mGoodsWantTv;
    TextView mGoodsLookTv;
    ConvenientBanner mBanner;
    CommentDialog dialog;
    TextView mTotalCountTv;




    public static void launch(Context context,int id){
        Intent intent = new Intent(context,GoodsDetailActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("详情").showBack().setRightImage(R.mipmap.ic_news_info_collect, view -> {
            getPresenter().collect(id);
        }).setRightImage2(R.mipmap.ic_news_info_share, view -> {

        }).show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_goods_detail);
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_goods_detail_header,mRecyclerView,false);
        mHeadIv = headerView.findViewById(R.id.iv_head);
        mUserTv = headerView.findViewById(R.id.tv_user);
        mTagTv = headerView.findViewById(R.id.tv_tag);
        mUserDescTv = headerView.findViewById(R.id.tv_user_desc);
        mGoodsDescTv = headerView.findViewById(R.id.tv_goods_info);
        mGoodsPriceTv = headerView.findViewById(R.id.tv_goods_price);
        mGoodsZanTv = headerView.findViewById(R.id.tv_zan);
        mGoodsWantTv = headerView.findViewById(R.id.tv_want);
        mGoodsLookTv = headerView.findViewById(R.id.tv_look);
        mTotalCountTv = headerView.findViewById(R.id.tv_comment_count);
        mBanner = headerView.findViewById(R.id.banner);
        mAdapter.addHeaderView(headerView);
        mAdapter.setHeaderAndEmpty(true);
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();


    }

    @Override
    protected void onRefresh() {
        mCurrentPage = 0;
        getPresenter().getGoodsInfo(id);
        getPresenter().getCommentList(id,mCurrentPage);
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mCurrentPage++;
        getPresenter().getCommentList(id,mCurrentPage);
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return true;
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id",0);
        mAdapter = new GoodsCommentAdapter();


    }

    @Override
    protected GoodsDetailPresenter getPresenter() {
        return new GoodsDetailPresenter(this);
    }

    @Override
    public void showGoodsInfo(GoodsPojo data) {
        LoadOption option = new LoadOption().setRoundRadius(SizeUtils.dp2px(this,4));
        ImageManager.getInstance().loadNet(data.getBilliardsUsers().getHeadImage(),mHeadIv,option);
        mUserTv.setText(data.getBilliardsUsers().getUserName());
        mTagTv.setVisibility(View.GONE);
        mUserDescTv.setText("");
        mGoodsDescTv.setText(data.getRemark());
        mGoodsPriceTv.setText("￥"+data.getPrice());
        mGoodsZanTv.setText(data.getPraiseCount()+"");
        mGoodsWantTv.setText(data.getWantCount()+"想要");
//        mGoodsLookTv.setText("");
        if (CollectionUtils.isEmpty(data.getBilliardsSecondMallImagesList())){
            mBanner.setVisibility(View.GONE);
        }else {
            ViewUtils.loadBanner(data.getBilliardsSecondMallImagesList(),mBanner,false,false,false);
        }
    }

    @Override
    public void showCommentList(List<GoodsComment> data,int totalCount) {
        if (mCurrentPage == 0){
            mAdapter.setNewData(data);
        }else {
            mAdapter.addData(data);
        }
        mTotalCountTv.setText("留言·"+totalCount);
    }

    @Override
    public void showCommentSuccess(String msg) {
        ToastUtils.showToast(this,msg);
        dialog.dismiss();
        onRefresh();
    }

    @Override
    public void showWantSuccess(String msg) {
        ToastUtils.showToast(this,msg);
        onRefresh();
    }

    @OnClick({R.id.btn_comment,R.id.btn_zan,R.id.btn_want})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_comment:
                dialog = new CommentDialog(this);
                dialog.setHint("看对眼就留言，问问更多细节~");
                dialog.setOnCommentListener(content -> {
                    getPresenter().comment(id,content);
                });
                dialog.show();
                break;
            case R.id.btn_want:
                getPresenter().want(id);
                break;
            case R.id.btn_zan:
                getPresenter().appreciate(id);
                break;
        }
    }
}
