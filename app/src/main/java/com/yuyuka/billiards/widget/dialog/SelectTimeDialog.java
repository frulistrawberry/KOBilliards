package com.yuyuka.billiards.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.SelectTimePojo;
import com.yuyuka.billiards.ui.activity.merchant.OrderConfirmActivity;
import com.yuyuka.billiards.ui.adapter.room.SelectTimeAdapter;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.StateButton;

import java.util.Date;
import java.util.List;

public class SelectTimeDialog extends Dialog implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    private SelectTimeAdapter mAdapter;
    private List<SelectTimePojo> mData;
    private StateButton reserveBtn;
    private TextView mTimeTv;
    private TextView mDurationTv;
    private TextView mTotalPriceTv;
    private TextView mPriceTv;
    private TextView mGoodsNameTv;
    private String goodsName;
    private String goodsInfo;
    private String duration;
    private int hours;
    private double price;
    private String id;
    private double singlePrice;
    private long time;


    public SelectTimeDialog( Context context) {
        this(context, R.style.BottomDialog);
    }

    public SelectTimeDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAdapter = new SelectTimeAdapter();
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_select_time, null);
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
        mGoodsNameTv = contentView.findViewById(R.id.tv_goods_name);
        reserveBtn = contentView.findViewById(R.id.btn_reserve);
        mDurationTv = contentView.findViewById(R.id.tv_duration);
        mTimeTv = contentView.findViewById(R.id.tv_time);
        mTotalPriceTv = contentView.findViewById(R.id.tv_total_price);
        mPriceTv = contentView.findViewById(R.id.tv_goods_price);
        reserveBtn.setOnClickListener(this);
        reserveBtn.setCanClick(false);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width=(getWindow().getWindowManager().getDefaultDisplay().getWidth());
        params.height= SizeUtils.dp2px(context,370);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @SuppressLint("SetTextI18n")
    public  void  setData(List<SelectTimePojo> data, String goodsName, long time, String price,String goodsInfo,String id){
        this.mData = data;
        mAdapter.setNewData(mData);
        this.goodsName = goodsName;
        mGoodsNameTv.setText(goodsName);
        this.goodsInfo = goodsInfo;
        this.time = time;
        if (DateUtils.formatDate(time).equals(DateUtils.currentDate())){
            mTimeTv.setText("今天("+ DateUtils.date2Str(new Date(time),DateUtils.MM_DD)+")");
        }else {
            mTimeTv.setText(DateUtils.date2Str(new Date(time),DateUtils.EEEE)+ "("+DateUtils.date2Str(new Date(time),DateUtils.MM_DD)+")");
        }
        mPriceTv.setText("￥"+price);
        this.id = id;
        this.singlePrice =Double.valueOf( data.get(0).getAmount());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        boolean hasChoose = false;
        int firstChooseP = 0;
        int lastChooseP = 0;
        for (int i = 0; i < mData.size(); i++) {
            hasChoose = mData.get(i).isSelected();
            if (hasChoose){
                firstChooseP = i;
                break;
            }
        }
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelected())
            lastChooseP = i;
        }
        if (hasChoose){
            if (mData.get(position).isSelected()){
                if (position==firstChooseP || position == lastChooseP){
                    mData.get(position).setSelected(false);
                }
            }else {
                if (Math.abs(firstChooseP-position)==1 || Math.abs(lastChooseP-position) == 1){
                    mData.get(position).setSelected(true);
                }
            }
        }else {
            mData.get(position).setSelected(true);
        }
        mAdapter.setNewData(mData);
        int first = 0;
        int last = 0;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelected()){
                first = i;
                break;
            }
        }
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelected()){
                last = i;
            }
        }
        if (Math.abs(last - first)<1){
            mDurationTv.setText("");
        }else {
            mDurationTv.setText(mData.get(first).getClock()+"-"+mData.get(last).getClock()+"("+(last-first)+"小时)");
            this.duration = mData.get(first).getClock()+"-"+mData.get(last).getClock();
            this.hours = last - first;
        }
        Double totalPrice = 0.0;
        int index = 0;
        for (SelectTimePojo mDatum : mData) {
            if (mDatum.isSelected()){
                if (index == last)
                    continue;
                totalPrice+=Double.valueOf(mDatum.getAmount());
            }
            index++;

        }
        this.price = totalPrice;
        reserveBtn.setCanClick(totalPrice>0);
        mTotalPriceTv.setText("共￥"+ DataOptionUtils.getStringWithRound(totalPrice+""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reserve:
                OrderConfirmActivity.launcher(getContext(),goodsName,goodsInfo,duration,id,price,hours,singlePrice,time);
                dismiss();
                break;
        }
    }
}
