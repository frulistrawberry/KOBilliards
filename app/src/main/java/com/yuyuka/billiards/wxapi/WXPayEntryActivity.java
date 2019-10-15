package com.yuyuka.billiards.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.yuyuka.billiards.constants.Config;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者: 陈冠希
 * 日期: 2018/3/26
 * 描述:
 */

public class WXPayEntryActivity  extends WXCallbackActivity {



    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            EventBus.getDefault().post(resp);
            if (resp.errCode == 0){
                ToastUtils.showToast(WXPayEntryActivity.this,"支付成功");
            }else {
                LogUtil.d("WeChatPay","errCode=" + resp.errCode);
                ToastUtils.showToast(WXPayEntryActivity.this,"支付失败");

            }
            finish();
        }
        finish();
    }
}
