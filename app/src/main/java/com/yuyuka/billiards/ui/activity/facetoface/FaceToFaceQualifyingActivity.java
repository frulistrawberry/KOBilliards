package com.yuyuka.billiards.ui.activity.facetoface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.SizeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class FaceToFaceQualifyingActivity extends BaseActivity {
    @BindView(R.id.v_status)
    View mStatusBar;
    @BindView(R.id.iv_scan)
    ImageView scanIv;
    long tableId;
    int battleId;
    int refOrderId;
    int competitionType;



    public static void launcher(Context context, long tableId, int battleId, int refOrderId,int competitionType){
        Intent intent = new Intent(context,FaceToFaceQualifyingActivity.class);
        intent.putExtra("tableId",tableId);
        intent.putExtra("battleId",battleId);
        intent.putExtra("refOrderId",refOrderId);
        intent.putExtra("competitionType",competitionType);
        context.startActivity(intent);
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
        EventBus.getDefault().register(this);
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

    }
}
