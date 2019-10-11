package com.yuyuka.billiards.ui.activity.table;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.widget.StateButton;

import butterknife.BindView;
import butterknife.OnClick;

public class OpenModeActivity extends BaseActivity {

    @BindView(R.id.iv_check1)
    ImageView check1;
    @BindView(R.id.iv_check2)
    ImageView check2;
    @BindView(R.id.iv_check3)
    ImageView check3;
    @BindView(R.id.btn_next)
    StateButton nextBtn;
    @BindView(R.id.tv_deoisit_price)
    TextView deoisitPriceTv;

    int mode = 2;
    double deoisitPrice;
    long billiardsPoolTable;
    String billiardsName;

    public static void launcher(Context context,long billiardsPoolTable,double deoisitPrice,String billiardsName){
        Intent intent = new Intent(context,OpenModeActivity.class);
        intent.putExtra("deoisitPrice",deoisitPrice);
        intent.putExtra("billiardsPoolTable",billiardsPoolTable);
        intent.putExtra("billiardsName",billiardsName);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_open_mode);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        deoisitPriceTv.setText("需要押金:"+deoisitPrice+"元");
    }

    @Override
    protected void initData() {
        deoisitPrice = getIntent().getDoubleExtra("deoisitPrice",0);
        billiardsPoolTable = getIntent().getLongExtra("billiardsPoolTable",0);
        billiardsName = getIntent().getStringExtra("billiardsName");
    }

    @OnClick({R.id.btn_back,R.id.btn_mode1,R.id.btn_mode2,R.id.btn_mode3,R.id.btn_next})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_mode1:
                resetCheck();
                check1.setImageResource(R.mipmap.ic_check_select);
                mode = 1;
                nextBtn.setText("去办理");
                break;
            case R.id.btn_mode2:
                resetCheck();
                check2.setImageResource(R.mipmap.ic_check_select);
                mode = 2;
                nextBtn.setText("去支付");
                break;
            case R.id.btn_mode3:
                resetCheck();
                check3.setImageResource(R.mipmap.ic_check_select);
                mode = 3;
                nextBtn.setText("去充值");
                break;
            case R.id.btn_next:
                if (mode == 2){
                    //押金支付
                    TablePayActivity.launcher(this,billiardsPoolTable, CompetitionType.OPEN_TABLE);
                }
                break;

        }
    }

    private void resetCheck(){
        check1.setImageResource(R.mipmap.ic_check_unslected);
        check2.setImageResource(R.mipmap.ic_check_unslected);
        check3.setImageResource(R.mipmap.ic_check_unslected);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK)
            finish();
    }
}
