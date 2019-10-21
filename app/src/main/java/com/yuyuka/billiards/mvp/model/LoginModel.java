package com.yuyuka.billiards.mvp.model;

import com.yuyuka.billiards.base.BaseModel;
import com.yuyuka.billiards.constants.UrlConstant;
import com.yuyuka.billiards.mvp.contract.login.LoginContract;
import com.yuyuka.billiards.net.BizContent;
import com.yuyuka.billiards.net.HttpResult;
import com.yuyuka.billiards.net.RequestParam;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.ILoginModel {
    @Override
    public Observable<HttpResult> getVerifyCode(String phoneNum) {
        BizContent content = new BizContent();
        content.setMobile(phoneNum);
        RequestParam requestParam = new RequestParam(UrlConstant.VERIFICATION_CODE,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> login(String phoneNum, String verifyCode) {
        BizContent content = new BizContent();
        content.setMobile(phoneNum);
        content.setSmsCode(verifyCode);
        RequestParam requestParam = new RequestParam(UrlConstant.LOGIN,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }

    @Override
    public Observable<HttpResult> thirdLogin(String loginName, String wxId, String headImage, String realName, String userName, int phoneNum) {
        BizContent content = new BizContent();
        content.setLoginName(loginName);
        content.setWxId(wxId);
        content.setHeadImage(headImage);
        content.setRealName(realName);
        content.setUserName(userName);
        content.setPhoneNum(0);
        RequestParam requestParam = new RequestParam(UrlConstant.THIRD_LOGIN,convertBizContent(content));
        return mService.simpleRequest(requestParam);
    }
}
