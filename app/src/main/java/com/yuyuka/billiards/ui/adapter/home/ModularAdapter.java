package com.yuyuka.billiards.ui.adapter.home;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.ModularPojo;
import com.yuyuka.billiards.ui.activity.facetoface.FaceToFaceActivity;
import com.yuyuka.billiards.ui.activity.course.KOClassActivity;
import com.yuyuka.billiards.ui.activity.market.MarketActivity;
import com.yuyuka.billiards.ui.activity.match.NearbyMatchActivity;
import com.yuyuka.billiards.ui.activity.merchant.AddMerchantActivity;
import com.yuyuka.billiards.ui.activity.merchant.NearbyMerchantActivity;
import com.yuyuka.billiards.ui.activity.news.ArticleDetailActivity;
import com.yuyuka.billiards.ui.activity.ranking.RunkingActivity;
import com.yuyuka.billiards.ui.fragment.message.RankingListFragment;

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
                        NearbyMerchantActivity.launcher(mContext);
                        break;
                    case "附近比赛":
                        NearbyMatchActivity.launcher(mContext);
                        break;
                    case "台球二手":
                        MarketActivity.launcher(mContext);
                        break;
                    case "KO学堂":
                        mContext.startActivity(new Intent(mContext, KOClassActivity.class));
                        break;
                    case "个人模式":
                        mContext.startActivity(new Intent(mContext, KOClassActivity.class));
                        break;
                    case "添加商户":
                        mContext.startActivity(new Intent(mContext, AddMerchantActivity.class));
                        break;
                    case "面对面对战":
                        mContext.startActivity(new Intent(mContext, FaceToFaceActivity.class));
                        break;
                    case "排行榜":
                        RunkingActivity.launcher(mContext);
                         break;

            }
        });
    }
}
