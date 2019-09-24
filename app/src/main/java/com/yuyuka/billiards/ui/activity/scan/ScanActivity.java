package com.yuyuka.billiards.ui.activity.scan;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.ToastUtils;

public class ScanActivity extends BaseActivity {


    @Override
    protected void initView() {
        setContentView(R.layout.activity_scan);
        mStatusBar.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            //下面这一行呢是android4.0起推荐大家使用的将布局延伸到状态栏的代码，配合5.0的设置状态栏颜色可谓天作之合
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        CaptureFragment captureFragment = new CaptureFragment();

        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                if (result.startsWith("rank")){
                    ToastUtils.showToast(getContext(),"排位赛扫码");
                }
            }

            @Override
            public void onAnalyzeFailed() {

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initData() {

    }
}
