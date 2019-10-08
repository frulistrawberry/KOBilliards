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
        helper.setText(R.id.tv_name,item.getcType());
        ImageView imageView = helper.getView(R.id.iv_img);
        ImageManager.getInstance().loadNet(item.getAddress(),imageView);

        helper.getConvertView().setOnClickListener(v -> KOListActivity.launcher(mContext,Integer.valueOf(item.getId()),item.getcType()));
    }
}
