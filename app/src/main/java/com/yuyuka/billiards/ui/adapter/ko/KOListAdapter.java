package com.yuyuka.billiards.ui.adapter.ko;


import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.KOListPojo;
import com.yuyuka.billiards.ui.activity.room.RoomDetailActivity;
import com.yuyuka.billiards.utils.DataOptionUtils;

public class KOListAdapter extends BaseQuickAdapter<KOListPojo, BaseViewHolder> {


    public KOListAdapter() {
        super(R.layout.item_ko_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, KOListPojo item) {
        if (helper.getAdapterPosition() - getHeaderLayoutCount() == 0){
            helper.setGone(R.id.v_divider,true);
        }else {
            helper.setGone(R.id.v_divider,false);
        }

        ImageView headIv = helper.getView(R.id.iv_img);
        ImageManager.getInstance().loadNet(item.getImageAdd(),headIv);

//
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_info,item.getInfo());

//        LinearLayout levelLayout = helper.getView(R.id.ll_level);
//        for (int i = 0; i < levelLayout.getChildCount(); i++) {
//            ImageView imageView = (ImageView) levelLayout.getChildAt(i);
//            if (i<item.getBillLevel()){
//                imageView.setImageResource(R.mipmap.ic_start_light);
//            }else {
//                imageView.setImageResource(R.mipmap.ic_start_dark);
//            }
//        }
//        helper.setText(R.id.tv_distance, DataOptionUtils.calculateLineDistance(item.getPositionLatitude(),item.getPositionLongitude()));
//        if (item.getTag() == null || item.getTag().isEmpty()){
//            helper.setGone(R.id.ll_service,false);
//            helper.setGone(R.id.ll_tag,false);
//        } else{
//            helper.setGone(R.id.ll_service,true);
//        }
//        helper.setText(R.id.tv_comment_count,item.getAppraiseCount()+"条");
//        helper.setText(R.id.tv_location,item.getPosition());
//        if (item.getDoBusiness() == 1){
//            helper.setTextColor(R.id.tv_business_status,mContext.getResources().getColor(R.color.text_color_10));
//            helper.setText(R.id.tv_business_status,"(营业中)");
//
//        }else if (item.getDoBusiness() == 2){
//            helper.setTextColor(R.id.tv_business_status,mContext.getResources().getColor(R.color.text_color_1));
//            helper.setText(R.id.tv_business_status,"(打烊)");
//        }else {
//            helper.setText(R.id.tv_business_status,"");
//        }
//
//
//
//        helper.getConvertView().setOnClickListener(v -> RoomDetailActivity.launcher(mContext,item.getBilliardsId()));
    }
}
