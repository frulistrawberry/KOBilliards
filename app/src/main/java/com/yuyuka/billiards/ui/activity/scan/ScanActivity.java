package com.yuyuka.billiards.ui.activity.scan;

import android.content.Intent;
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
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.table.TableContract;
import com.yuyuka.billiards.mvp.presenter.table.TablePresenter;
import com.yuyuka.billiards.pojo.OrderPojo;
import com.yuyuka.billiards.pojo.TablePojo;
import com.yuyuka.billiards.ui.activity.pay.TablePayActivity;
import com.yuyuka.billiards.ui.activity.table.TableActivity;
import com.yuyuka.billiards.utils.NetworkUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import java.util.Map;
import java.util.Objects;


public class ScanActivity extends BaseMvpActivity<TablePresenter> implements TableContract.ITableView {

    int refOrderId;
    int battleId;
    long tableId;
    String backUrl;

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
                LogUtil.e("scan",result);
                Map<String,String> params = NetworkUtils.URLRequest(result);
                tableId = Long.valueOf(Objects.requireNonNull(params.get("tableNum")));
                backUrl = params.get("backUrl");
                if (backUrl.equals(UrlConstant.BACK_URL_OPEN_TABLE)){
                    TableActivity.launcher(getContext(),tableId);
                    finish();
                }else {
                    refOrderId = Integer.valueOf(Objects.requireNonNull(params.get("refOrderId")));
                    battleId = Integer.valueOf(Objects.requireNonNull(params.get("battleId")));
                    getPresenter().getTableInfo(tableId);
                }
            }

            @Override
            public void onAnalyzeFailed() {
                ToastUtils.showToast(getContext(),"扫描错误，请重试");
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected TablePresenter getPresenter() {
        return new TablePresenter(this);
    }

    @Override
    public void showTableInfo(TablePojo data) {
        if (data.getDeoisitPrice()>0){
            TablePayActivity.launcher(this,battleId,refOrderId);
        }else {
            getPresenter().enterMatch(battleId,refOrderId,2);
        }
    }

    @Override
    public void showOrderSuccess(OrderPojo data) {

    }

    @Override
    public void showOrderFailure(String msg) {

    }

    @Override
    public void showEnterSuccess() {
        // TODO: 2019-10-11 进入对战页面
    }

    @Override
    public void showEnterFailure() {

    }
}
