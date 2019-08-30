package com.yuyuka.billiards.ui.adapter.ko;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.KOListPojo;

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

        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_info,item.getInfo());

    }
}
