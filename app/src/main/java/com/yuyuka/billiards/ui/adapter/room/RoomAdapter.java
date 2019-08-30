package com.yuyuka.billiards.ui.adapter.room;


import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.ui.activity.merchant.MerchantDetailActivity;
import com.yuyuka.billiards.utils.DataOptionUtils;

public class RoomAdapter extends BaseQuickAdapter<BilliardsRoomPojo, BaseViewHolder> {


    public RoomAdapter() {
        super(R.layout.item_billiards_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsRoomPojo item) {
        if (helper.getAdapterPosition() - getHeaderLayoutCount() == 0){
            helper.setGone(R.id.v_divider,true);
        }else {
            helper.setGone(R.id.v_divider,false);
        }

        ImageView headIv = helper.getView(R.id.iv_img);
        ImageManager.getInstance().loadNet(item.getHeadImage(),headIv);

        helper.setText(R.id.tv_billiards_name,item.getBilliardsName());
        LinearLayout levelLayout = helper.getView(R.id.ll_level);
        for (int i = 0; i < levelLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) levelLayout.getChildAt(i);
            if (i<item.getBillLevel()){
                imageView.setImageResource(R.mipmap.ic_start_light);
            }else {
                imageView.setImageResource(R.mipmap.ic_start_dark);
            }
        }
        helper.setText(R.id.tv_distance, DataOptionUtils.calculateLineDistance(item.getPositionLatitude(),item.getPositionLongitude()));
        helper.setText(R.id.tv_business_date,"营业时间:"+item.getBusinessDate());
        helper.setText(R.id.tv_minimum_payment,"￥"+item.getMinimumPayment());
        if (item.getTag() == null || item.getTag().isEmpty()){
            helper.setGone(R.id.ll_service,false);
            helper.setGone(R.id.ll_tag,false);
        } else{
            helper.setGone(R.id.ll_service,true);
        }
        helper.setText(R.id.tv_comment_count,item.getAppraiseCount()+"条");
        helper.setText(R.id.tv_location,item.getPosition());
        if (item.getDoBusiness() == 1){
            helper.setTextColor(R.id.tv_business_status,mContext.getResources().getColor(R.color.text_color_10));
            helper.setText(R.id.tv_business_status,"(营业中)");

        }else if (item.getDoBusiness() == 2){
            helper.setTextColor(R.id.tv_business_status,mContext.getResources().getColor(R.color.text_color_1));
            helper.setText(R.id.tv_business_status,"(打烊)");
        }else {
            helper.setText(R.id.tv_business_status,"");
        }



        helper.getConvertView().setOnClickListener(v -> MerchantDetailActivity.launcher(mContext,item.getBilliardsId()));
    }
}
