package com.yuyuka.billiards.ui.activity.merchant;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseRefreshActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.merchant.RoomDetailContract;
import com.yuyuka.billiards.mvp.presenter.merchant.RoomDetailPresenter;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.RoomInfoPojo;
import com.yuyuka.billiards.pojo.SelectTimePojo;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.merchant.AssistantListFragment;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.ScreenUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ViewUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.ObservableNestedScrollView;
import com.yuyuka.billiards.widget.dialog.SelectTimeDialog;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MerchantDetailActivity extends BaseRefreshActivity<RoomDetailPresenter> implements RoomDetailContract.IRoomDetailView {


    private String[] mTitle = {"球台预定","球房活动","球友点评","设施亮点"};
    private String[] mDateTitle;
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.scroll_view)
    ObservableNestedScrollView mScrollView;
    @BindView(R.id.ll_reserve)
    LinearLayout mReserveLayout;
    @BindView(R.id.ll_active)
    LinearLayout mActiveLayout;
    @BindView(R.id.ll_comment)
    LinearLayout mCommentLayut;
    @BindView(R.id.ll_facilities)
    LinearLayout mFacilitiesLayout;
    @BindView(R.id.ll_star)
    LinearLayout mStarLayout;
    @BindView(R.id.tab_layout_reserve)
    MagicIndicator mReserveTabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.ll_room_desc)
    LinearLayout mllroom;
    @BindView(R.id.container_reserve)
    LinearLayout mReserveContainer;
    boolean canRefresh = true;
    int billiardsId;
    int weekNum;

    public static void launcher(Context context,int billiardsId){
        Intent intent = new Intent(context, MerchantDetailActivity.class);
        intent.putExtra("billiardsId",billiardsId);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房详情").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_detail);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return  mTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.text_color_1));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.text_color_2));
                simplePagerTitleView.setText(mTitle[index]);
                simplePagerTitleView.setOnClickListener(v -> {
                    int reserveTop = mReserveLayout.getTop();
                    int activeTop = mActiveLayout.getTop();
                    int facilitiesTop = mFacilitiesLayout.getTop()-100;
                    int commentTop = mCommentLayut.getTop();
                    mAppbarLayout.setExpanded(false);
                    switch (index){
                        case 0:
                            mScrollView.scrollTo(0,reserveTop);
                            break;
                        case 1:
                            mScrollView.scrollTo(0,activeTop);
                            break;
                        case 2:
                            mScrollView.scrollTo(0,commentTop);
                            break;
                        case 3:
                            mScrollView.scrollTo(0,facilitiesTop);
                            break;
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.tab_indicator_start_color),getResourceColor(R.color.tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mTabLayout.setNavigator(commonNavigator);

        CommonNavigator weekNavigator = new CommonNavigator(getContext());
        weekNavigator.setAdjustMode(true);
        weekNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return  mDateTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.text_color_3));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.text_color_6));
                simplePagerTitleView.setText(mDateTitle[index]);
                simplePagerTitleView.setTextSize(12);
                simplePagerTitleView.setSingleLine(false);
                simplePagerTitleView.setGravity(Gravity.CENTER);
                simplePagerTitleView.setPadding(0,0,0, SizeUtils.dp2px(getContext(),5));
                simplePagerTitleView.setOnClickListener(v -> {
                    mReserveTabLayout.onPageSelected(index);
                    mReserveTabLayout.onPageScrolled(index,0,0);
                    long today = System.currentTimeMillis();
                    long temp = 60*60*24*1000*index+today;
                    String date = DateUtils.date2Str(new Date(temp),DateUtils.YYYY_MM_DD);
                    weekNum = DateUtils.dateToWeek(date);
                    getPresenter().getGoodsInfo(billiardsId,weekNum,true);

                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.reserve_tab_indicator_start_color),getResourceColor(R.color.reserve_tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mReserveTabLayout.setNavigator(weekNavigator);
        List<Fragment> coachList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            coachList.add(AssistantListFragment.newFragment(0));
        }
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(this)));
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),coachList));
        mScrollView.setScrollViewListener((scrollView, x, y, oldx, oldy) -> {
            int reserveTop = mReserveLayout.getTop();
            int reserveBottom = mReserveLayout.getBottom();
            int activeTop = mActiveLayout.getTop();
            int activeBottom = mStarLayout.getBottom();
            int facilitiesTop = mFacilitiesLayout.getTop()-100;
            int facilitiesBottom = mFacilitiesLayout.getBottom()-100;
            int commentBottom = mCommentLayut.getBottom()-100;
            int commentTop = mCommentLayut.getTop();

            if (y>=reserveTop && y<=reserveBottom){
                mTabLayout.onPageSelected(0);
                mTabLayout.onPageScrolled(0,0,0);

            }
            if (y>=activeTop && y<=activeBottom){
                mTabLayout.onPageSelected(1);
                mTabLayout.onPageScrolled(1,0,0);

            }

            if (y>=commentTop && y<=commentBottom){
                mTabLayout.onPageSelected(2);
                mTabLayout.onPageScrolled(2,0,0);

            }

            if (y>=facilitiesTop && y<=facilitiesBottom){
                mTabLayout.onPageSelected(3);
                mTabLayout.onPageScrolled(3,0,0);

            }

        });
        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                canRefresh = state == State.EXPANDED;
            }
        });
        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefresh();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return canRefresh;
            }
        });

        onRefresh();
    }

    @Override
    public void onRefresh() {
        getPresenter().getGoodsInfo(billiardsId,weekNum,false);
    }

    @Override
    protected void initData() {
        mDateTitle = getDateTitles();
        weekNum = DateUtils.dateToWeek(DateUtils.currentDate());
        billiardsId = getIntent().getIntExtra("billiardsId",0);

    }

    @OnClick({R.id.rl_comment,R.id.images,R.id.ll_facilities})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_comment:
                MerchantCommentActivity.launcher(this);
                break;
            case R.id.images:
                AlbumActivity.launcher(this);
                break;
            case R.id.ll_facilities:
                MerchantFacilitiesActivity.launcher(this);
                break;

        }
    }

    private String[] getDateTitles(){
        String[] result = new String[5];
        long today = System.currentTimeMillis();
        for (int i = 0; i < result.length; i++) {
            long temp = 60*60*24*1000*i+today;
            String date = DateUtils.date2Str(new Date(temp),DateUtils.MM_DD);
            String week = DateUtils.date2Str(new Date(temp),DateUtils.EEEE);
            if (i==0)
                week = "今天";
            result[i] = date+"\n"+week;
        }
        return result;
    }

    @Override
    protected RoomDetailPresenter getPresenter() {
        return new RoomDetailPresenter(this);
    }

    @Override
    public void showRoomInfo(RoomInfoPojo info) {

    }

    @Override
    public void showGoodsInfo(List<BilliardsGoods> goods) {
        mReserveContainer.removeAllViews();
        if (CollectionUtils.isEmpty(goods)){
            View emptyView = ViewUtils.genEmptyView(this,R.mipmap.ic_empty,"空空如也");
            emptyView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SizeUtils.dp2px(this,300)));
            mReserveContainer.addView(emptyView);
        }else {

            for (BilliardsGoods good : goods) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_reserve,null);
                ImageView goodsIv = itemView.findViewById(R.id.iv_img);
                TextView goodsNameTv = itemView.findViewById(R.id.tv_goods_name);
                TextView goodsInfoTv = itemView.findViewById(R.id.tv_goods_info);
                TextView promotionTv = itemView.findViewById(R.id.tv_protion);
                TextView amountTv = itemView.findViewById(R.id.tv_amount);
                ImageManager.getInstance().loadNet(good.getGoodsImage(),goodsIv);
                goodsNameTv.setText(good.getGoodsName());
                goodsInfoTv.setText(good.getGoodsInfo());
                if (CollectionUtils.isEmpty(good.getBilliardsPromotionList())){
                    promotionTv.setText("");
                }else {
                    List<BilliardsGoods.BilliardsPromotionList> promotionList = good.getBilliardsPromotionList();
                    StringBuilder protionSb = new StringBuilder();
                    protionSb.append("优惠时段")
                            .append(promotionList.get(0).getClock())
                            .append(":00");
                    if (promotionList.size()>1){
                        protionSb.append("-")
                                .append(promotionList.get(promotionList.size()-1))
                                .append(":00");
                    }
                    protionSb.append("\n")
                            .append("优惠时段")
                            .append(promotionList.get(0).getAmount())
                            .append("元/小时");
                    promotionTv.setText(protionSb);
                }
                amountTv.setText("￥"+good.getGoodsAmount());
                itemView.setOnClickListener(v -> {
                    SelectTimeDialog dialog = new SelectTimeDialog(getContext());
                    List<SelectTimePojo> data = new ArrayList<>();
                    for (int i = 0; i < good.getBilliardsGoodsScheduledTimeDtoList().size(); i++) {
                        boolean hasActive = false;
                        SelectTimePojo selectTimePojo = new SelectTimePojo();
                        selectTimePojo.setSelected(false);
                        selectTimePojo.setActive(false);
                        selectTimePojo.setAmount(good.getGoodsAmount()+"");
                        selectTimePojo.setClock(good.getBilliardsGoodsScheduledTimeDtoList().get(i).getClock()+":00");

                        for (int i1 = 0; i1 < good.getBilliardsPromotionList().size(); i1++) {

                            if (good.getBilliardsPromotionList().get(i1).getClock() == good.getBilliardsGoodsScheduledTimeDtoList().get(i).getClock()){
                                selectTimePojo.setActive(true);
                                hasActive = true;
                                selectTimePojo.setAmount(good.getBilliardsPromotionList().get(i1).getAmount()+"");
                                break;
                            }
                        }
                        selectTimePojo.setActive(hasActive);
                    }
                    dialog.setData(data);
                    dialog.show();

                });
                mReserveContainer.addView(itemView);
            }
        }
    }
}
