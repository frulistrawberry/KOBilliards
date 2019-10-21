package com.yuyuka.billiards.ui.activity.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.merchant.RoomDetailContract;
import com.yuyuka.billiards.mvp.presenter.merchant.RoomDetailPresenter;
import com.yuyuka.billiards.pojo.BilliardsGoods;
import com.yuyuka.billiards.pojo.BilliardsRoomPojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.ui.activity.pay.TablePaySuccessActivity;
import com.yuyuka.billiards.ui.activity.room.PaySuesscesActivity;
import com.yuyuka.billiards.utils.PayUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseMvpActivity<RoomDetailPresenter> implements RoomDetailContract.IRoomDetailView {

    String money;
    String name;

    int id;
    String beginDate;
    String endDate;
    String remark;
    @BindView(R.id.tv_name)
    TextView nameTv;
    @BindView(R.id.tv_money)
    TextView moneyTv;
    int setmId;
    public static void launcher(Activity context, int id, String name, String money, String beginDate, String endDate, String remark,int setmId){
        Intent intent = new Intent(context,PayActivity.class);
        intent.putExtra("money",money);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        intent.putExtra("setmId",setmId);
        intent.putExtra("beginDate",beginDate);
        intent.putExtra("endDate",endDate);
        intent.putExtra("remark",remark);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("支付订单").showBack().show();
    }

    @Override
    protected void initView() {
        setTitleStyle(1);
        setContentView(R.layout.activity_pay);
        nameTv.setText(name);
        moneyTv.setText(money);
    }

    @Override
    protected void initData() {
        money = getIntent().getStringExtra("money");
        name = getIntent().getStringExtra("name");
        beginDate = getIntent().getStringExtra("beginDate");
        endDate = getIntent().getStringExtra("endDate");
        remark = getIntent().getStringExtra("remark");
        id = getIntent().getIntExtra("id",0);
        setmId = getIntent().getIntExtra("setmId",0);
        LogUtil.e("data","id="+id);
        LogUtil.e("data","money="+money);
        LogUtil.e("data","beginDate="+beginDate);
        LogUtil.e("data","endDate="+endDate);
        LogUtil.e("data","remark="+remark);
        EventBus.getDefault().register(this);
    }


    @OnClick(R.id.btn_pay)
    public void onClcik(View v){
        switch (v.getId()){
            case R.id.btn_pay:
                getPresenter().testRevert(setmId,remark,beginDate,endDate,id);
                break;
        }
    }

    @Override
    protected RoomDetailPresenter getPresenter() {
        return new RoomDetailPresenter(this);
    }

    @Override
    public void showRoomInfo(BilliardsRoomPojo info) {

    }

    @Override
    public void showGoodsInfo(List<BilliardsGoods> goods) {

    }

    @Override
    public void showCollectSuccess(String msg) {

    }

    @Override
    public void showCollectFailure(String msg) {

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



    @Subscribe
    public void onEvent(BaseResp message){

        if (message.errCode == 0){
            TablePaySuccessActivity.launcher(this,null,0);
            setResult(RESULT_OK);
            finish();
        }


    }


}
