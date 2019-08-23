package com.yuyuka.billiards.ui.activity.facetoface;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.SizeUtils;

import butterknife.BindView;

public class FaceToFaceQualifyingActivity extends BaseActivity {
    @BindView(R.id.v_status)
    View mStatusBar;
    @BindView(R.id.iv_scan)
    ImageView scanIv;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_face_to_face_qualifying);
        super.mStatusBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            mStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(this)));
        }else {
            mStatusBar.setVisibility(View.GONE);
        }
        Bitmap bitmap = CodeUtils.createImage("asdfasdfa", SizeUtils.dp2px(this,165),SizeUtils.dp2px(this,165),null);

        scanIv.setImageBitmap(bitmap);
    }

    @Override
    protected void initData() {

    }
}
