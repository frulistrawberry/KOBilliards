package com.yuyuka.billiards.wxapi;



import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;

public class WXEntryActivity extends WXCallbackActivity {

    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0){
                ToastUtils.showToast(WXEntryActivity.this,"支付成功");
            }else {
                LogUtil.d("WeChatPay","errCode=" + resp.errCode);
                ToastUtils.showToast(WXEntryActivity.this,"支付成功");

            }
            finish();
        }
    }
}
