package com.yuyuka.billiards.ui.activity.facetoface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.constants.CompetitionType;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.ui.activity.table.BattleActivity;
import com.yuyuka.billiards.ui.activity.table.SingleBattleActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class BattleWaitActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView, BaseActivity.OnKeyClickListener {
    @BindView(R.id.v_status)
    View mStatusBar;
    @BindView(R.id.iv_scan)
    ImageView scanIv;
    long tableId;
    int battleId;
    int refOrderId;
    int competitionType;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.battlename)
    TextView battleName;
    @BindView(R.id.battletype)
    TextView battleType;



    public static void launcher(Context context, long tableId, int battleId, int refOrderId,int competitionType){
        Intent intent = new Intent(context, BattleWaitActivity.class);
        intent.putExtra("tableId",tableId);
        intent.putExtra("battleId",battleId);
        intent.putExtra("refOrderId",refOrderId);
        intent.putExtra("competitionType",competitionType);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_back,R.id.btn_cancel})
    public void onClick(View v){
        if (v.getId() == R.id.btn_cancel||v.getId()==R.id.btn_back)
            getPresenter().cancelOrder(battleId);

    }




    @Override
    protected void initView() {
        setContentView(R.layout.activity_face_to_face_qualifying);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

        if (competitionType == CompetitionType.FACE_TO_FACE_RANK || competitionType == CompetitionType.SCAN_RANK){
            title.setText("面对面排位赛");
            battleType.setText("请对方扫码达成面对面排位赛");
            battleName.setText("排位赛抢5\n" + "中式八球（赛规）");
        }else {
            title.setText("面对面娱乐赛");
            battleType.setText("扫码加入面对面娱乐赛");
            battleName.setText("九局五胜制\n" + "中式八球（赛规）");
        }

        setOnKeyClickListener(this);

        String stringBuilder = UrlConstant.API_HOST + UrlConstant.SCAN_PATCH +
                "tableNum=" +
                tableId +
                "&battleId=" +
                battleId +
                "&refOrderId=" +
                refOrderId +
                "&payChannel=2" +
                "&backUrl=" +
                UrlConstant.BACK_URL_ENTER_MATCH;
        Bitmap bitmap = CodeUtils.createImage(stringBuilder, SizeUtils.dp2px(this,155),SizeUtils.dp2px(this,155),null);

        scanIv.setImageBitmap(bitmap);
    }

    @Override
    protected void initData() {
        tableId = getIntent().getLongExtra("tableId",0);
        battleId = getIntent().getIntExtra("battleId",0);
        refOrderId = getIntent().getIntExtra("refOrderId",0);
        competitionType = getIntent().getIntExtra("competitionType",0);

    }

    @Subscribe
    public void onEvent(CustomNotification notification){
        super.onEvent(notification);
        CustomNoticePojo data = new Gson().fromJson(notification.getContent(),CustomNoticePojo.class);
        if (data.getNoticeType() == 6 || data.getNoticeType() == 1){
            dismissProgressDialog();
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

    @Override
    public void clickBack() {
        getPresenter().cancelOrder(battleId);
    }
}
