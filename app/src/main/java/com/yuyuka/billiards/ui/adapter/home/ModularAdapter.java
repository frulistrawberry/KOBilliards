package com.yuyuka.billiards.ui.adapter.home;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.ModularPojo;
import com.yuyuka.billiards.ui.activity.mail.MailActivity;
import com.yuyuka.billiards.ui.activity.match.NearbyMatchActivity;
import com.yuyuka.billiards.ui.activity.room.NearbyRoomActivity;

;

public class ModularAdapter extends BaseQuickAdapter<ModularPojo, BaseViewHolder> {


    public ModularAdapter() {
        super(R.layout.item_modular);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModularPojo item) {
        helper.setImageResource(R.id.iv_img,item.getIcon());
        helper.setText(R.id.tv_text,item.getTitle());
        helper.getConvertView().setOnClickListener(v -> {
            switch (item.getTitle()){
                    case "附近球房":
                        NearbyRoomActivity.launcher(mContext);
                        break;
                    case "附近比赛":
                        NearbyMatchActivity.launcher(mContext);
                        break;
                    case "台球二手":
                        MailActivity.launcher(mContext);
                        break;
            }
        });
    }
}
