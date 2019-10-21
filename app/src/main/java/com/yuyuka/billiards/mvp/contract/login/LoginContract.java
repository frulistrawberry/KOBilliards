package com.yuyuka.billiards.mvp.contract.login;

import com.yuyuka.billiards.base.IBaseModel;
import com.yuyuka.billiards.base.IBaseView;
import com.yuyuka.billiards.net.HttpResult;

import io.reactivex.Observable;

public interface LoginContract {
    interface ILoginView extends IBaseView{
        void showVerifyCodeSuccess();
        void showVerifyCodeFailure(String errMsg);
        void showLoginSuccess();
        void showLoginFailure(String msg);
    }

    interface ILoginModel extends IBaseModel{
        Observable<HttpResult> getVerifyCode(String phoneNum);
        Observable<HttpResult> login(String phoneNum,String verifyCode);
        Observable<HttpResult> thirdLogin(String loginName,String wxId,String headImage,String realName,String userName,int phoneNum);
    }
}
