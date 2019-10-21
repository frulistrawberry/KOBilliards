package com.yuyuka.billiards.ui.activity.merchant;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.merchant.OrderConfirmContract;
import com.yuyuka.billiards.mvp.presenter.merchant.OrderConfirmPresenter;
import com.yuyuka.billiards.pojo.GoodsAmount;
import com.yuyuka.billiards.pojo.TaoCan;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.widget.dialog.ReserveConfirmDialog;

import java.util.Date;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.OnClick;

public class OrderConfirmActivity extends BaseMvpActivity<OrderConfirmPresenter> implements OrderConfirmContract.IOrderConfirmView {
    @BindView(R.id.ll_detail)
    LinearLayout mDetailLayout;
    String id;
    String goodsName;
    String goodsInfo;
    String duration;
    double price;
    int hours;
    @BindView(R.id.tv_goods_name)
    TextView mGoodsNameTv;
    @BindView(R.id.tv_goods_info)
    TextView mGoodsInfoTv;
    @BindView(R.id.tv_duration)
    TextView mDurationTv;
    @BindView(R.id.ll_taocan)
    LinearLayout mSetMealLayout;
    @BindView(R.id.tv_pay_price)
    TextView mPayPriceTv;
    @BindView(R.id.tv_pay_price1)
    TextView mPayPriceTv1;
    @BindView(R.id.tv_single_price)
    TextView mSinglePriceTv;
    double singlePrice;
    @BindView(R.id.tv_detail)
    TextView mDetailTv;
    @BindView(R.id.tv_price_detail)
    TextView mPriceDetailTv;
    long time;
    @BindView(R.id.iv_arrow)
    ImageView mArrowIv;
    @BindView(R.id.et_beizhu)
    EditText beizhuEt;

    int goodsId;
    private String starDate;
    private String endDate;
    private String billiardsName;
    private int setmID;

    public static void launcher(Context context,String goodsName,String goodsInfo,String duration,String id,double price,int hours,double singlePrice,long time,int goodsId,String startDate,String endDate,String billiardsName){
        Intent intent = new Intent(context, OrderConfirmActivity.class);
        intent.putExtra("goodsName",goodsName);
        intent.putExtra("goodsInfo",goodsInfo);
        intent.putExtra("duration",duration);
        intent.putExtra("id",id);
        intent.putExtra("price",price);
        intent.putExtra("hours",hours);
        intent.putExtra("singlePrice",singlePrice);
        intent.putExtra("time",time);
        intent.putExtra("goodsId",goodsId);
        intent.putExtra("beginDate",startDate);
        intent.putExtra("endDate",endDate);
        intent.putExtra("billiardsName",billiardsName);

        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("预定").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_reserve_detail);
        mGoodsNameTv.setText(goodsName);
        mGoodsInfoTv.setText(goodsInfo);
        mDurationTv.setText(duration);

        getPresenter().getSetmeal(id);
        getPresenter().getGoodsAmount(goodsId,starDate,endDate);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        goodsName = getIntent().getStringExtra("goodsName");
        goodsInfo = getIntent().getStringExtra("goodsInfo");
        duration = getIntent().getStringExtra("duration");
        price = getIntent().getDoubleExtra("price",0);
        singlePrice = getIntent().getDoubleExtra("singlePrice",0);
        hours = getIntent().getIntExtra("hours",0);
        time = getIntent().getLongExtra("time",0);
        goodsId = getIntent().getIntExtra("goodsId",0);
        starDate = getIntent().getStringExtra("beginDate");
        endDate = getIntent().getStringExtra("endDate");
        billiardsName = getIntent().getStringExtra("billiardsName");

    }

    @OnClick({R.id.btn_detail,R.id.btn_confirm,R.id.v_touch})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_detail:
                if (mDetailLayout.getVisibility() == View.VISIBLE){
                    mDetailLayout.setVisibility(View.GONE);
                    mArrowIv.setImageResource(R.mipmap.small_arrow_up);
                }
                else{
                    mArrowIv.setImageResource(R.mipmap.small_arrow_down);
                    mDetailLayout.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.v_touch:
                mArrowIv.setImageResource(R.mipmap.small_arrow_up);
                mDetailLayout.setVisibility(View.GONE);

                break;
            case R.id.btn_confirm:
                ReserveConfirmDialog dialog =  new ReserveConfirmDialog(this);
                String time1 = "";
                if (DateUtils.formatDate(time).equals(DateUtils.currentDate())){
                    time1 = DateUtils.date2Str(new Date(time),DateUtils.MM_DD)+"(今天)";
                }else {
                    time1 = DateUtils.date2Str(new Date(time),DateUtils.MM_DD)+"("+DateUtils.date2Str( new Date(time),DateUtils.EEEE)+")";
                }
                dialog.setData("预定时间"+duration,"预定日期"+time1,goodsId,billiardsName,mPayPriceTv.getText().toString(),starDate,endDate,beizhuEt.getText().toString(),setmID);
                dialog.show();
                break;
        }
    }

    @Override
    protected OrderConfirmPresenter getPresenter() {
        return new OrderConfirmPresenter(this);
    }

    @Override
    public void showPackages(List<TaoCan> data) {
        mSetMealLayout.removeAllViews();
        View setMealView = LayoutInflater.from(this).inflate(R.layout.item_taocan,null);
        ImageView defSelect = setMealView.findViewById(R.id.iv_select);
        TextView defName = setMealView.findViewById(R.id.tv_name);
        TextView defPrice = setMealView.findViewById(R.id.tv_price);
        defSelect.setImageResource(R.mipmap.ic_check_select);
        TextView infoView = setMealView.findViewById(R.id.tv_info);
        infoView.setVisibility(View.GONE);
        defName.setText("预定时段,无任何套餐");
        defPrice.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mPayPriceTv.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mPayPriceTv1.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mSetMealLayout.addView(setMealView);
        if (!CollectionUtils.isEmpty(data)){
            for (TaoCan datum : data) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_taocan,null);
                ImageView select = view.findViewById(R.id.iv_select);
                TextView name = view.findViewById(R.id.tv_name);
                TextView info = view.findViewById(R.id.tv_info);
                TextView price = view.findViewById(R.id.tv_price);
                name.setText(datum.getMealName());
                info.setText(datum.getMealInfo());
                price.setText("￥"+ DataOptionUtils.getStringWithRound(String.valueOf(this.price+datum.getMealAmount())));
                select.setImageResource(R.mipmap.ic_check_unslected);
                mSetMealLayout.addView(view);

            }
        }
        for (int i = 0; i < mSetMealLayout.getChildCount(); i++) {
            View view = mSetMealLayout.getChildAt(i);
            int finalI = i;
            view.setOnClickListener(v -> {
                for (int i1 = 0; i1 < mSetMealLayout.getChildCount(); i1++) {
                    View view1 = mSetMealLayout.getChildAt(i1);
                    ImageView selectIv1 = view1.findViewById(R.id.iv_select);
                    selectIv1.setImageResource(R.mipmap.ic_check_unslected);
                }
                ImageView selectIv = view.findViewById(R.id.iv_select);
                selectIv.setImageResource(R.mipmap.ic_check_select);
                if (finalI !=0){
                    mPayPriceTv.setText("￥"+DataOptionUtils.getStringWithRound((this.price+data.get(finalI-1).getMealAmount())+""));
                    mPayPriceTv1.setText("￥"+DataOptionUtils.getStringWithRound((this.price+data.get(finalI-1).getMealAmount())+""));
                }
                else {

                    mPayPriceTv.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
                    mPayPriceTv1.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
                }
                if (finalI>0){
                    setmID = data.get(finalI -1).getId();
                }

            });

        }


    }

    @Override
    public void showAmount(GoodsAmount data) {
        price = data.getPayAmount();
        mPayPriceTv.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mPayPriceTv1.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mSinglePriceTv.setText("￥"+DataOptionUtils.getStringWithRound(price+""));
        mDetailTv.setText(duration+("("+hours+"小时)"));
        mPriceDetailTv.setText(hours+"x"+DataOptionUtils.getStringWithRound(singlePrice+""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            finish();
    }
}
