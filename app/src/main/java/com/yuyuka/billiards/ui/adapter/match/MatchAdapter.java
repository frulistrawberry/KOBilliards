package com.yuyuka.billiards.ui.adapter.match;

import android.widget.ImageView;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.pojo.BilliardsMatchPojo;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.DateUtils;

import java.util.Date;

public class MatchAdapter extends BaseQuickAdapter<BilliardsMatchPojo, BaseViewHolder> {

    public MatchAdapter() {
        super(R.layout.item_match_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, BilliardsMatchPojo item) {
        BilliardsMatchPojo.BilliardsInfo billiardsInfo = item.getBilliardsInfo();
        ImageView headImageAddIv = helper.getView(R.id.iv_head_image_add);
        ImageManager.getInstance().loadNet(item.getHeadImageAdd(),headImageAddIv);
        helper.setText(R.id.tv_match_name,item.getMatchName());
        helper.setText(R.id.tv_position,"比赛地点:"+billiardsInfo.getPosition());
        helper.setText(R.id.tv_distance, DataOptionUtils.calculateLineDistance(billiardsInfo.getPositionLatitude(),billiardsInfo.getPositionLongitude()));
        helper.setText(R.id.tv_begin_date,"比赛时间:"+item.getBeginDate());
        helper.setText(R.id.tv_total_bonus, "比赛奖金:"+item.getTotalBonus()+"元");
        String dayDiff = DateUtils.getDayDiff(new Date(),DateUtils.parseDatetime(item.getEndDate()));
        helper.setText(R.id.tv_day_diff, dayDiff);
        ProgressBar progressBar = helper.getView(R.id.progress_bar);
        if (DateUtils.isAfter(new Date(),DateUtils.parseDatetime(item.getEndDate()))){
            progressBar.setProgress(100);
        }else if (DateUtils.isBefore(new Date(),DateUtils.parseDatetime(item.getBeginDate()))){
            progressBar.setProgress(0);
        }else {
            long progress = DateUtils.parseDatetime(item.getEndDate()).getTime() - System.currentTimeMillis();
            long progressTotal = DateUtils.parseDatetime(item.getEndDate()).getTime() -DateUtils.parseDatetime(item.getBeginDate()).getTime();
            progressBar.setProgress((int) (progress/progressTotal));
        }




    }
}
