package com.yuyuka.billiards.ui.activity.facetoface;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.BarUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class FaceToFaceActivity extends BaseActivity {
    @BindView(R.id.v_status)
    View mStatusBar;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_face_to_face);
        getStatusbar().setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_paiwei,R.id.iv_yule})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_paiwei:
                startActivity(new Intent(this,FaceToFaceQualifyingActivity.class));
                break;
            case R.id.iv_yule:
                startActivity(new Intent(this,FaceToFaceFunActivity.class));
                break;

        }
    }


}
