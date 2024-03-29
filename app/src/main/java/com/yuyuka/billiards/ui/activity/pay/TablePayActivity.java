package com.yuyuka.billiards.ui.activity.pay;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.model.TableModel;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.utils.PayUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class TablePayActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView {

    public static final int PAY_FOR_ORDER = 1;
    public static final int PAY_FOR_ENTER_MATCH = 2;
    public static final int PAY_FOR_TABLE = 3;


    @BindView(R.id.tv_name)
    TextView nameTv;
    @BindView(R.id.tv_money)
    TextView moneyTv;
    @BindView(R.id.iv_wx_check)
    ImageView wxCheck;
    @BindView(R.id.iv_ali_check)
    ImageView aliCheck;
    @BindView(R.id.tv_desc)
            TextView descTv;
    @BindView(R.id.tv_info)
            TextView infoTv;
    @BindView(R.id.llyajin)
    LinearLayout yajinll;
    @BindView(R.id.tablename)
    TextView tablename;
    @BindView(R.id.battlename)
    TextView battlename;
    @BindView(R.id.paymoney)
    TextView paymoney;
    @BindView(R.id.order)
    TextView order;

    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.yajin)
    TextView yajin;
    @BindView(R.id.lltaifei)
    LinearLayout taifeill;


    int payChannel = 0;
    long tableId;
    int competitionType;
    int id;
    int refOrderId;

    int payFor = 1;
    CustomNoticePojo data;



    public static void launcher(Activity context,long tableId,int competitionType){
        Intent intent = new Intent(context,TablePayActivity.class);
        intent.putExtra("tableId",tableId);
        intent.putExtra("competitionType",competitionType);
        intent.putExtra("payFor",1);
        context.startActivityForResult(intent,0);
    }

    public static void launcher(Activity context,long tableId,int id,int refOrderId){
        Intent intent = new Intent(context,TablePayActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("refOrderId",refOrderId);
        intent.putExtra("tableId",tableId);
        intent.putExtra("payFor",2);
        context.startActivityForResult(intent,0);
    }

    public static void launcher(Activity context,CustomNoticePojo data){
        Intent intent = new Intent(context,TablePayActivity.class);
        intent.putExtra("data",data);
        intent.putExtra("payFor",3);
        context.startActivityForResult(intent,0);
    }


    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("支付订单").showBack().showDivider().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_table_pay);
        if (payFor == 3){
            yajinll.setVisibility(View.GONE);
            taifeill.setVisibility(View.VISIBLE);
            tablename.setText(data.getBizContent().getBilliardsName());
            order.setText("订单号:"+data.getBizContent().getOrderId());
            date.setText("时间:"+data.getBizContent().getData());
            position.setText("地点:"+data.getBizContent().getPosition());
            battlename.setText("排位赛");
            paymoney.setText("￥"+data.getBizContent().getPayAmount() == null?"-.-":data.getBizContent().getPayAmount()+"");
            yajin.setText("押金金额:￥"+data.getBizContent().getDeoisitprice()+"元");
        }else {
            yajinll.setVisibility(View.VISIBLE);
            taifeill.setVisibility(View.GONE);
            getPresenter().getTableInfo(tableId);
        }
    }

    @Override
    protected void initData() {
        tableId = getIntent().getLongExtra("tableId",0);
        competitionType = getIntent().getIntExtra("competitionType",0);
        payFor = getIntent().getIntExtra("payFor",1);
        id = getIntent().getIntExtra("id",0);
        refOrderId = getIntent().getIntExtra("refOrderId",0);
        data = (CustomNoticePojo) getIntent().getSerializableExtra("data");
    }

    @OnClick({R.id.btn_wx_pay,R.id.btn_ali_pay,R.id.btn_pay})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_wx_pay:
                wxCheck.setImageResource(R.mipmap.ic_tablepay_check);
                aliCheck.setImageResource(R.mipmap.ic_tablepay_un_check);
                payChannel = 0;
                break;
            case R.id.btn_ali_pay:
                aliCheck.setImageResource(R.mipmap.ic_tablepay_check);
                wxCheck.setImageResource(R.mipmap.ic_tablepay_un_check);
                payChannel = 1;
                break;
            case R.id.btn_pay:
                if (payFor == PAY_FOR_ORDER)
                    getPresenter().openTable(tableId,payChannel,competitionType);
                else if (payFor == PAY_FOR_ENTER_MATCH)
                    getPresenter().enterMatch(id,refOrderId,payChannel);
                else if (payFor == PAY_FOR_TABLE){
                    getPresenter().settle(data.getBizContent().getBattle().getId(),payChannel);
                }
                break;

        }
    }

    @Override
    protected TablePresenter getPresenter() {
        return new TablePresenter(this);
    }

    @Override
    public void showTableInfo(TablePojo data) {
        nameTv.setText(data.getBilliardsInfo().getBilliardsName());
        moneyTv.setText("￥"+data.getDeoisitPrice());
    }

    @Override
    public void showOrderSuccess(OrderPojo data) {
        OrderPojo.OrderInfo orderInfo = data.getOrderInfo();

        if (data.getOrderInfo()!=null){
            if (data.getPayChannel() == 0){
                PayUtils.getInstance().wxPay(this,orderInfo.getPrepayId(),orderInfo.getNonceStr(),orderInfo.getTimeStamp(),orderInfo.getPaySign());
            }
        }

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

    @Subscribe
    public void onEvent(CustomNotification message){
        String json = message.getContent();
        CustomNoticePojo data = new Gson().fromJson(json,CustomNoticePojo.class);
        TablePaySuccessActivity.launcher(this,data,tableId);
        setResult(RESULT_OK);
        finish();


    }



}
