package com.yuyuka.billiards.ui.activity.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BattleResultActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView {

    @BindView(R.id.chart)
    RadarChart chart;


    CustomNoticePojo data;

    String duanwei = "入门";

    boolean win;

    @BindView(R.id.btn_send)
    StateButton commitBtn;
    @BindView(R.id.rl_duijujiesuan)
    RelativeLayout duijujiesuan;
    @BindView(R.id.rl_xiangxishuju)
    RelativeLayout xiangxishuju;
    @BindView(R.id.iv_result)
    ImageView resultIv;
    @BindView(R.id.name)
    TextView name;

    int[] colors = {Color.parseColor("#5FF199"),Color.parseColor("#F86B6D"),Color.parseColor("#F86B6D"),Color.parseColor("#BAA8FC"),Color.parseColor("#F86B6D")};

    public static void launcher(Activity context, CustomNoticePojo data, boolean win){
        Intent intent = new Intent(context,BattleResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",data);
        bundle.putBoolean("win",win);
        intent.putExtras(bundle);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void initView() {
        switch (duanwei){
            case "入门":
                setContentView(R.layout.activity_battle_win_2);
                break;
            case "新手":
                setContentView(R.layout.activity_battle_win_1);
                break;
            case "业余":
                setContentView(R.layout.activity_battle_win_3);
                break;
            case "导师":
                setContentView(R.layout.activity_battle_win_4);
                break;
            case "大师":
                setContentView(R.layout.activity_battle_win_5);
                break;
                default:
                    setContentView(R.layout.activity_battle_win_2);
                    break;
        }
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }
//        chartBanding();
        if (win){
            commitBtn.setText("返回");
            resultIv.setImageResource(R.mipmap.shengli);
        }
        else{
            commitBtn.setText("支付台费");
            resultIv.setImageResource(R.mipmap.shibai);
        }

        name.setText(duanwei+data.getBizContent().getUserRank().getCurrentDuan().getUserAt().getRank()+"段");


    }



    @Override
    protected void initData() {
        data = (CustomNoticePojo) getIntent().getExtras().getSerializable("data");
        win = getIntent().getExtras().getBoolean("win");
        CustomNoticePojo.UserRank rank = data.getBizContent().getUserRank();
        if (rank.getUserId() == CommonUtils.getUserId())
            duanwei = rank.getCurrentDuan().getName();

    }


    private void setData() {//设置数据
        float mult = 100;
        float min = 20;
        //项目数
        int cnt = 5;

        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }

    }

    private void chartBanding() {
        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);
        chart.setRotationEnabled(false);

        setData();
        chart.animateXY(//设置绘制动画
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setAxisMinValue(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"战绩(VRT)", "的分比", "攻击力", "防御力", "局长"};//设置雷达图普

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(getResourceColor(R.color.white));

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(1, false);
        yAxis.setTextSize(12f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);


    }

    @OnClick({R.id.iv_title_left,R.id.btn_send,R.id.btn_xiangxishuju,R.id.btn_duijujiesuan})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.btn_send:
                CustomNoticePojo.Battle battle = data.getBizContent().getBattle();
                if (battle.getBattleType() == CompetitionType.FACE_TO_FACE_BATTLE
                        || battle.getBattleType() == CompetitionType.FACE_TO_FACE_RANK) {
                    getPresenter().pushOrder(data.getBizContent().getBattle().getId());
                }else if (win){
                    setResult(RESULT_OK);
                    finish();
                }else {
                    getPresenter().pushOrder(data.getBizContent().getBattle().getId());
                }
                break;
            case R.id.btn_xiangxishuju:
                xiangxishuju.setVisibility(View.VISIBLE);
                duijujiesuan.setVisibility(View.GONE);
                break;
            case R.id.btn_duijujiesuan:
                duijujiesuan.setVisibility(View.VISIBLE);
                xiangxishuju.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public void onEvent(CustomNotification notification) {
        super.onEvent(notification);
        CustomNoticePojo data = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (data.getNoticeType() == 6){
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected TablePresenter getPresenter() {
        return new TablePresenter(this);
    }

    @Override
    public void showTableInfo(TablePojo data) {

    }

    @Override
    public void showOrderSuccess(OrderPojo data) {

    }

    @Override
    public void showOrderFailure(String msg) {

    }

    @Override
    public void showEnterSuccess() {

    }

    @Override
    public void showEnterFailure() {

    }
}
