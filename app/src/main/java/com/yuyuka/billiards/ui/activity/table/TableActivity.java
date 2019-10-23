package com.yuyuka.billiards.ui.activity.table;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.yuyuka.billiards.ui.activity.facetoface.BattleWaitActivity;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class TableActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView {

    @BindView(R.id.v_status)
    View mStatusBar;
    long tableId;
    @BindView(R.id.tv_table_name)
    TextView tableNameTv;
    @BindView(R.id.tv_table_brand)
    TextView tableBrandTv;
    @BindView(R.id.tv_table_mode)
    TextView tableModeTv;
    @BindView(R.id.tv_table_type)
    TextView tableTypeTv;
    @BindView(R.id.tv_hour_price)
    TextView hourPriceTv;
    @BindView(R.id.tv_sink_type)
    TextView sinkTypeTv;
    @BindView(R.id.tv_outer_size)
    TextView outerSizeTv;
    @BindView(R.id.tv_inter_size)
    TextView interSizeTv;

    double deoisitPrice;
    String billiardsName;

    public static void launcher(Context context,long tableId){
        Intent intent = new Intent(context,TableActivity.class);
        intent.putExtra("tableId",tableId);
        context.startActivity(intent);
    }

    @Override
    protected TablePresenter getPresenter() {
        return new TablePresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_table);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        getPresenter().getTableInfo(tableId);

    }

    @Override
    protected void initData() {
        tableId = getIntent().getLongExtra("tableId",0);
    }


    @Override
    public void showTableInfo(TablePojo data) {
        tableNameTv.setText(data.getTableName());
        tableBrandTv.setText("球座品牌:"+data.getTableBrand());
        tableModeTv.setText("球座型号:"+data.getTableModel());
        tableTypeTv.setText("球桌类别:"+data.getTableType());
        sinkTypeTv.setText("球座库型:"+data.getSinkType());
        outerSizeTv.setText("外径尺寸:"+data.getOuterSize());
        interSizeTv.setText("外径尺寸:"+data.getInterSize());
        hourPriceTv.setText("球台费用:"+data.getBilliardsCostRules().getHourPrice()+"/小时");
        deoisitPrice = data.getDeoisitPrice();
        billiardsName = data.getBilliardsInfo().getBilliardsName();
    }

    @OnClick({R.id.btn_back,R.id.btn_open,R.id.btn_begin_rank,R.id.btn_begin_battle,R.id.btn_call_service,R.id.btn_member_recharge,R.id.btn_integral_recharge})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_open:
                if (deoisitPrice>0)
                    OpenModeActivity.launcher(this,tableId,deoisitPrice,billiardsName);
                else
                    getPresenter().openTable(tableId,0,CompetitionType.OPEN_TABLE);
                break;
            case R.id.btn_begin_rank:
                if (deoisitPrice>0){
                    TablePayActivity.launcher(this,tableId,CompetitionType.SCAN_RANK);
                }else {
                    getPresenter().openTable(tableId,2,CompetitionType.SCAN_RANK);
                }
                break;
            case R.id.btn_begin_battle:
                if (deoisitPrice>0){
                    TablePayActivity.launcher(this,tableId,CompetitionType.SCAN_BATTLE);
                }else {
                    getPresenter().openTable(tableId,2,CompetitionType.SCAN_BATTLE);
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void showOrderSuccess(OrderPojo data) {

    }

    @Override
    public void showOrderFailure(String msg) {
        showError(msg);
    }

    @Override
    public void showEnterSuccess() {

    }

    @Override
    public void showEnterFailure() {

    }


    boolean isResume = false;

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }
}
