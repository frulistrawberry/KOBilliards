package com.yuyuka.billiards.ui.activity.merchant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseRefreshActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.mvp.contract.merchant.RoomDetailContract;
import com.yuyuka.billiards.mvp.presenter.merchant.RoomDetailPresenter;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.ImagePojo;
import com.yuyuka.billiards.pojo.ListData;
import com.yuyuka.billiards.pojo.NewsCommentItem;
import com.yuyuka.billiards.pojo.RoomInfoPojo;
import com.yuyuka.billiards.pojo.SelectTimePojo;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.merchant.AssistantListFragment;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.ScreenUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;
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


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MerchantDetailActivity extends BaseRefreshActivity<RoomDetailPresenter> implements RoomDetailContract.IRoomDetailView {


    private String[] mTitle = {"球台预定","球房活动","球友点评","设施亮点"};
    private String[] mDateTitle;
    private long[] mTimeLongs;
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
    String billiardsId;
    int weekNum;
    private int curIndex;
    @BindView(R.id.tv_billiards_name)
    TextView mBilliardsNameTv;
    @BindView(R.id.tv_location)
    TextView mLocationTv;
    @BindView(R.id.tv_distance)
    TextView mDistanceTv;
    @BindView(R.id.tv_business_date)
    TextView mBusinessTv;
    @BindView(R.id.tv_business_status)
    TextView mBusinessStatusTv;
    @BindView(R.id.iv_business_status)
    ImageView mBusinessStatusIv;
    @BindView(R.id.tv_level)
    TextView mLevelTv;
    @BindView(R.id.tv_level_text)
    TextView mLevelTextTv;
    @BindView(R.id.tv_photo_count)
    TextView mPhotoCountTv;
    @BindView(R.id.banner)
    ConvenientBanner mBanner;
    private List<BilliardsGoods> goodsList;

    public static void launcher(Context context,String billiardsId){
        Intent intent = new Intent(context, MerchantDetailActivity.class);
        intent.putExtra("billiardsId",billiardsId);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房详情").setRightImage2(R.mipmap.ic_news_info_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).setRightImage(R.mipmap.ic_news_info_collect, view -> {
            getPresenter().collect(Integer.valueOf(billiardsId));
        }).showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
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
                    showGoodsInfo(goodsList);
                    curIndex = index;

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
        getPresenter().getMerchantInfo(billiardsId);
        getPresenter().getGoodsInfo(billiardsId,weekNum);
    }

    @Override
    protected void initData() {
        mDateTitle = getDateTitles();
        weekNum = DateUtils.dateToWeek(DateUtils.currentDate());
        billiardsId = getIntent().getStringExtra("billiardsId");

    }

    @OnClick({R.id.rl_comment,R.id.images,R.id.ll_facilities})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_comment:
                MerchantCommentActivity.launcher(this,billiardsId,"球房名称");
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
        mTimeLongs = new long[5];
        long today = System.currentTimeMillis();
        for (int i = 0; i < result.length; i++) {
            long temp = 60*60*24*1000*i+today;
            mTimeLongs[i] = temp;
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
    public void showRoomInfo(BilliardsRoomPojo info) {
        mBilliardsNameTv.setText(info.getBilliardsName());
        mLocationTv.setText(info.getPosition());
        mDistanceTv.setText(DataOptionUtils.calculateLineDistance(info.getPositionLatitude(),info.getPositionLongitude()));
        mBusinessTv.setText(info.getBusinessDate());
        if (info.getDoBusiness() == 1){
            mBusinessStatusIv.setImageResource(R.drawable.circle_green);
            mBusinessStatusTv.setText("营业中");
        }else {
            mBusinessStatusIv.setImageResource(R.drawable.circle_gray);
            mBusinessStatusTv.setText("打烊了");
        }

        mLevelTv.setText(info.getBillLevel()+".0");
        if (info.getBillLevel()>=5)
            mLevelTextTv.setText("极好");
        if (info.getBillLevel()>=4){
            mLevelTextTv.setText("很好");
        }else {
            mLevelTextTv.setText("一般");
        }
        if (CollectionUtils.isEmpty(info.getBilliardsImages())){
            ImagePojo imagePojo = new ImagePojo(info.getHeadImage());
            List<ImagePojo> imagePojos = new ArrayList<>();
            imagePojos.add(imagePojo);
            mPhotoCountTv.setVisibility(View.GONE);
            ViewUtils.loadBanner(imagePojos,mBanner,true,false,false);
        }else {
            mPhotoCountTv.setText("照片"+info.getBilliardsImages().size()+"张");
            ViewUtils.loadBanner(info.getBilliardsImages(),mBanner,true,false,false);

        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showGoodsInfo(List<BilliardsGoods> goods) {
        goodsList = goods;
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
                if (!TextUtils.isEmpty(good.getGoodsInfo()))
                    goodsInfoTv.setText(good.getGoodsInfo());
                List<BilliardsGoods.BilliardsPromotionRulesList> promotionList =  new ArrayList<>();
                promotionTv.setText("");
                if (good.getBilliardsPromotionRulesInfo()!=null){
                    promotionTv.setText("优惠时间"+good.getMinPrice()+"元/小时");
                    String weekJson = good.getBilliardsPromotionRulesInfo().getWeekJson();
                    Type type = new TypeToken<List<BilliardsGoods.BilliardsPromotionRulesList>>(){}.getType();
                    List<BilliardsGoods.BilliardsPromotionRulesList> list = new Gson().fromJson(weekJson,type);
                    for (BilliardsGoods.BilliardsPromotionRulesList billiardsPromotionRulesList : list) {
                        if (billiardsPromotionRulesList.getWeek() == weekNum){
                            promotionList.add(billiardsPromotionRulesList);
                        }
                    }
                }
                if (good.getBilliardsCostRules()!=null){
                    amountTv.setText("￥"+good.getBilliardsCostRules().getHourPrice());
                }else {
                    amountTv.setText("_._");
                }
                itemView.setOnClickListener(v -> {
                    SelectTimeDialog dialog = new SelectTimeDialog(getContext());
                    List<SelectTimePojo> data = new ArrayList<>();
                    List<BilliardsGoods.BilliardsReserveRulesList> list = good.getBilliardsReserveRulesInfo().getBilliardsReserveRulesList();
                    List<BilliardsGoods.BilliardsReserveRulesList> reserveRulesList = new ArrayList<>();
                    for (BilliardsGoods.BilliardsReserveRulesList billiardsReserveRulesList : list) {
                        if (billiardsReserveRulesList.getWeekNum() == weekNum){
                            reserveRulesList.add(billiardsReserveRulesList);
                        }

                    }
                    for (int i = 0; i < reserveRulesList.size(); i++) {
                        boolean hasActive = false;
                        SelectTimePojo selectTimePojo = new SelectTimePojo();
                        selectTimePojo.setSelected(false);
                        selectTimePojo.setActive(false);
                        selectTimePojo.setAmount(good.getBilliardsCostRules().getHourPrice()+"");
                        selectTimePojo.setClock(reserveRulesList.get(i).getClock()+":00");
                        if (!CollectionUtils.isEmpty(promotionList)){
                            for (int i1 = 0; i1 < promotionList.size(); i1++) {

                                if (promotionList.get(i1).getClock() == promotionList.get(i).getClock()){
                                    selectTimePojo.setActive(true);
                                    hasActive = true;
                                    selectTimePojo.setAmount(good.getMinPrice()+"");
                                    break;
                                }
                            }
                        }
                        selectTimePojo.setActive(hasActive);
                        data.add(selectTimePojo);
                    }
                    dialog.setData(data,good.getGoodsName(),mTimeLongs[curIndex],good.getBilliardsCostRules().getHourPrice()+"","",billiardsId);
                    dialog.show();

                });
                mReserveContainer.addView(itemView);
            }
        }
    }

    @Override
    public void showCollectSuccess(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void showCollectFailure(String msg) {
        ToastUtils.showToast(this,msg);
    }
}
