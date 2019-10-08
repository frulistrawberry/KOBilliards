package com.yuyuka.billiards.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yuyuka.billiards.constants.Config;
import com.yuyuka.billiards.utils.bean.PayResult;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class PayUtils {

    public  void wxPay(Activity activity,String prepayId, String nonceStr, String timeStamp, String sign){
        IWXAPI wxapi =  WXAPIFactory.createWXAPI(activity, null);
        wxapi.registerApp(Config.WX_APP_ID);

        PayReq req = new PayReq();
        req.appId = Config.WX_APP_ID;
        req.partnerId = Config.WX_PARTENER_ID;
        req.prepayId = prepayId;
        req.nonceStr = nonceStr;
        req.timeStamp = timeStamp;
        req.packageValue = "Sign=WXPay";
        req.sign = sign;
        wxapi.sendReq(req);
    }

    public void aliPay(final Activity context, final String orderInfo){

        if (!checkAliPayInstalled(context)){
            ToastUtils.showToast("请先安装支付宝");
            return;
        }
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(context);
            Map<String, String> result = alipay.payV2(orderInfo, false);
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private static final int SDK_PAY_FLAG = 1;

    private static PayUtils mInstance;

    private Handler mHandler = new Handler(msg -> {
        switch (msg.what) {
            case SDK_PAY_FLAG: {
                @SuppressWarnings("unchecked")
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus, "9000")) {
                    ToastUtils.showToast("支付成功");
                } else {
                    ToastUtils.showToast("支付失败");
                }
                break;
            }
        }
        return false;
    });

    public  boolean checkAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    public static synchronized PayUtils getInstance(){
        if (mInstance == null)
            mInstance = new PayUtils();
        return mInstance;
    }

}
