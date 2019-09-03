package com.yuyuka.billiards.ui.activity.match;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseRefreshActivity;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.match.MatchDetailContract;
import com.yuyuka.billiards.mvp.presenter.match.MatchDetailPresenter;
import com.yuyuka.billiards.mvp.presenter.match.MatchOrderConfirmActivity;
import com.yuyuka.billiards.pojo.ImagePojo;
import com.yuyuka.billiards.pojo.MatchBonusPojo;
import com.yuyuka.billiards.pojo.MatchDetailPojo;
import com.yuyuka.billiards.utils.AppUtils;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ViewUtils;
import com.yuyuka.billiards.widget.AppBarStateChangeListener;
import com.yuyuka.billiards.widget.ObservableNestedScrollView;
import com.yuyuka.billiards.widget.tabindicator.MagicIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.yuyuka.billiards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MatchDetailActivity extends BaseRefreshActivity<MatchDetailPresenter> implements MatchDetailContract.IMatchDetailView {

    String matchId;
    private String[] mTitle = {"赛事支持","报名须知","赛事规程","比赛股则"};
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;
    @BindView(R.id.scroll_view)
    ObservableNestedScrollView mScrollView;
    @BindView(R.id.ll_match_support)
    LinearLayout mSupportLayout;
    @BindView(R.id.ll_match_knows)
    LinearLayout mKnownsLayout;
    @BindView(R.id.ll_match_rules)
    LinearLayout mRulesLayout;
    @BindView(R.id.ll_match_flow)
    LinearLayout mFlowLayout;
    @BindView(R.id.ll_bonus)
    LinearLayout mBonusLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppbarLayout;
    boolean canRefresh = true;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.tv_match_name)
    TextView mMatchNameTv;
    @BindView(R.id.tv_match_merchant)
    TextView mMatchMerchantNameTv;
    @BindView(R.id.tv_match_address)
    TextView mMatchAddressTv;
    @BindView(R.id.tv_match_distance)
    TextView mMatchDistanceTv;
    @BindView(R.id.tv_match_date)
    TextView mMatchTimeTv;
    @BindView(R.id.tv_sign_end_time)
    TextView mMatchEndTimeTv;
    @BindView(R.id.tv_day_diff)
    TextView mDayDiffTv;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.tv_pay_price)
    TextView mPayPriceTv;

    public static void launch(Context context,String matchId){
        Intent intent = new Intent(context,MatchDetailActivity.class);
        intent.putExtra("matchId",matchId);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("比赛详情").showBack().show();
    }


    @Override
    protected void initView() {
        setContentView(R.layout.activity_match_detail);
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
                    int reserveTop = mSupportLayout.getTop();
                    int activeTop = mKnownsLayout.getTop();
                    int facilitiesTop = mRulesLayout.getTop()-100;
                    int commentTop = mFlowLayout.getTop();
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



        mScrollView.setScrollViewListener((scrollView, x, y, oldx, oldy) -> {
            int reserveTop = mSupportLayout.getTop();
            int reserveBottom = mSupportLayout.getBottom();
            int activeTop = mKnownsLayout.getTop();
            int activeBottom = mKnownsLayout.getBottom();
            int facilitiesTop = mRulesLayout.getTop()-100;
            int facilitiesBottom = mRulesLayout.getBottom()-100;
            int commentBottom = mFlowLayout.getBottom()-100;
            int commentTop = mFlowLayout.getTop();

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
    protected void onRefresh() {
        getPresenter().getMatchBonus(matchId,false);
        getPresenter().getMatchDetail(matchId,false);
    }

    @Override
    protected void initData() {
        matchId = getIntent().getStringExtra("matchId");
    }

    @Override
    protected MatchDetailPresenter getPresenter() {
        return new MatchDetailPresenter(this);
    }

    @OnClick({R.id.btn_confirm})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_confirm:
                MatchOrderConfirmActivity.launch(this);
                break;
        }
    }

    @Override
    public void showMatchDetail(MatchDetailPojo data) {
        if (data!=null){
            List<MatchDetailPojo.BilliardsMatchImagesList> bannerList = data.getBilliardsMatchImagesList();
            if (CollectionUtils.isEmpty(bannerList)){
                banner.setVisibility(View.GONE);
            }else {
                banner.setVisibility(View.VISIBLE);
                if (bannerList.size()>1){
                    banner.setCanLoop(true);
                    banner.startTurning(3000);
                }else {
                    banner.setCanLoop(false);
                }
                if (bannerList.size()>1){
                    banner.setPointViewVisible(true)
                            .setPageIndicator(new int[]{R.drawable.banner_indecator_nav, R.drawable.banner_indicator_act})
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
                }
                banner.setPages((CBViewHolderCreator<BannerHolder>) () -> new BannerHolder(false),bannerList);
            }
            mMatchNameTv.setText(data.getMatchName());
            if (data.getBilliardsInfo()!=null){
                mMatchMerchantNameTv.setText("比赛场地:"+data.getBilliardsInfo().getBilliardsName());
                mMatchAddressTv.setText("比赛地点:"+data.getBilliardsInfo().getPosition());
                mMatchDistanceTv.setText("直线距离"+ DataOptionUtils.calculateLineDistance(data.getBilliardsInfo().getPositionLatitude(),data.getBilliardsInfo().getPositionLongitude()));
            }
            mMatchTimeTv.setText("比赛时间:"+data.getBeginDate());
            mMatchEndTimeTv.setText("报名结束时间:"+data.getEndDate());

            String dayDiff = DateUtils.getDayDiff(new Date(),DateUtils.parseDatetime(data.getEndDate()));
            mDayDiffTv.setText(dayDiff);
            if (DateUtils.isAfter(new Date(),DateUtils.parseDatetime(data.getEndDate()))){
                progressBar.setProgress(100);
            }else if (DateUtils.isBefore(new Date(),DateUtils.parseDatetime(data.getBeginDate()))){
                progressBar.setProgress(0);
            }else {
                long progress = DateUtils.parseDatetime(data.getEndDate()).getTime() - System.currentTimeMillis();
                long progressTotal = DateUtils.parseDatetime(data.getEndDate()).getTime() -DateUtils.parseDatetime(data.getBeginDate()).getTime();
                progressBar.setProgress((int) (progress/progressTotal));
            }
            mPayPriceTv.setText(Html.fromHtml("报名费:<font color='#F46113'>"+data.getSignUp()+"元</font>"));

        }
    }

    @Override
    public void showMatchBonus(List<MatchBonusPojo> data) {
        int[] icons = {R.mipmap.ic_match_bonus_1,R.mipmap.ic_match_bonus_2,R.mipmap.ic_match_bonus_3,R.mipmap.ic_match_bonus_4};
        String[] ranks = {"冠军","亚军","季军","殿军"};
        String[] ranksEn = {"Champion","Runner-up","Third place","Fourth place"};
        mBonusLayout.removeAllViews();
        if (CollectionUtils.isEmpty(data))
            mBonusLayout.setVisibility(View.GONE);
        else {
            mBonusLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < 4; i++) {

                    View itemView = LayoutInflater.from(this).inflate(R.layout.item_match_bonus,null);
                    ImageView imageView = itemView.findViewById(R.id.iv_rank);
                    TextView rankIv = itemView.findViewById(R.id.tv_rank);
                    TextView rankEnIv = itemView.findViewById(R.id.tv_rank_en);
                    TextView bonusTv = itemView.findViewById(R.id.tv_bonus);
                    imageView.setImageResource(icons[i]);
                    rankIv.setText(ranks[i]);
                    rankEnIv.setText(ranksEn[i]);
                    if (i<data.size()){
                        bonusTv.setText(data.get(i).getMatchBonus()+"元");
                        itemView.setVisibility(View.VISIBLE);
                    }else {
                        itemView.setVisibility(View.INVISIBLE);
                    }
                    mBonusLayout.addView(itemView);
                    if (i!=3){
                        View view = new View(this);
                        view.setLayoutParams(new LinearLayout.LayoutParams(0,1,1));
                        mBonusLayout.addView(view);
                    }

            }
        }


    }

    public static class BannerHolder implements Holder<MatchDetailPojo.BilliardsMatchImagesList> {
        private ImageView mImageView;
        private LoadOption mOption;
        private boolean isCornerRound;

        public BannerHolder(boolean isCornerRound) {
            mOption = new LoadOption();
            this.isCornerRound = isCornerRound;
            if (isCornerRound)
                mOption.setRoundRadius(SizeUtils.dp2px(AppUtils.getAppContext(),5));
        }

        @Override
        public View createView(Context context) {
            mImageView= new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
            mImageView.setLayoutParams(params);
            if (!isCornerRound)
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return mImageView;
        }

        @Override
        public void UpdateUI(final Context context, int position, MatchDetailPojo.BilliardsMatchImagesList data) {
            String url = data.getImagesAdd();
            ImageManager.getInstance().loadNet(url,mImageView,mOption);
            mImageView.setOnClickListener(v -> {

            });
        }





    }
}
