package com.yuyuka.billiards.ui.adapter.ko;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.KOClassPojo;
import com.yuyuka.billiards.utils.SizeUtils;

public class KOTypeAdapter extends BaseQuickAdapter<KOClassPojo, BaseViewHolder> {
    public KOTypeAdapter() {
        super(R.layout.item_ko_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, KOClassPojo item) {
        LinearLayout parent = helper.getView(R.id.ll_parent);
//        if (helper.getAdapterPosition()%2 == 0){
//            parent.setPadding(SizeUtils.dp2px(mContext,8),0,SizeUtils.dp2px(mContext,2.5f),0);
//            parent.setGravity(Gravity.LEFT);
//        }else {
//            parent.setPadding(SizeUtils.dp2px(mContext,2.5f),0,SizeUtils.dp2px(mContext,8),0);
//            parent.setGravity(Gravity.RIGHT);
//        }
        helper.setText(R.id.tv_name,item.getcType());
        ImageView imageView = helper.getView(R.id.iv_img);
        ImageManager.getInstance().loadNet(item.getAddress(),imageView);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KOListActivity.launcher(mContext,Integer.valueOf(item.getId()),item.getcType());
            }
        });
    }
}
