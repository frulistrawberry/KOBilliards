package com.yuyuka.billiards.mvp.presenter.login;


import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.yuyuka.billiards.base.BasePresenter;
import com.yuyuka.billiards.mvp.contract.login.LoginContract;
import com.yuyuka.billiards.mvp.model.LoginModel;
import com.yuyuka.billiards.net.RespObserver;
import com.yuyuka.billiards.pojo.LoginPojo;
import com.yuyuka.billiards.pojo.UserInfo;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView, LoginContract.ILoginModel> {
    public LoginPresenter(LoginContract.ILoginView view) {
        super(view, new LoginModel());
    }

    public void getSmsCode(String phoneNum){
        getView().showProgressDialog();
        mModel.getVerifyCode(phoneNum)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                       getView().dismissProgressDialog();
                       getView().showVerifyCodeSuccess();
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showVerifyCodeFailure(errMsg);
                    }
                });
    }

    public void login(String phoneNum,String smsCode){
        getView().showProgressDialog();
        mModel.login(phoneNum,smsCode)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        LoginPojo data = new Gson().fromJson(bizEntity,LoginPojo.class);
                        CommonUtils.saveToken(data.getToken());
                        UserInfo userInfo = new Gson().fromJson(data.getUserInfo(),UserInfo.class);
                        CommonUtils.saveUserInfo(userInfo);

                        NIMClient.getService(AuthService.class).login(CommonUtils.getLoginInfo()).setCallback(new RequestCallback<LoginInfo>() {
                            @Override
                            public void onSuccess(LoginInfo param) {
                                getView().showLoginSuccess();
                                getView().dismissProgressDialog();
                            }

                            @Override
                            public void onFailed(int code) {
                                getView().showLoginFailure("IM登录失败,错误码"+code);
                                getView().dismissProgressDialog();
                            }

                            @Override
                            public void onException(Throwable exception) {

                            }
                        });
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showLoginFailure(errMsg);
                    }
                });
    }

    public void thirdLogin(String loginName,String wxId,String headImage,String realName,String userName,int phoneNum){
        getView().showProgressDialog();
        mModel.thirdLogin(loginName,wxId,headImage,realName,userName,phoneNum)
                .compose(RxUtils.transform(getView()))
                .subscribe(new RespObserver() {
                    @Override
                    public void onResult(String msg, String bizEntity) {
                        LoginPojo data = new Gson().fromJson(bizEntity,LoginPojo.class);
                        CommonUtils.saveToken(data.getToken());
                        UserInfo userInfo = new Gson().fromJson(data.getUserInfo(),UserInfo.class);
                        CommonUtils.saveUserInfo(userInfo);

                        NIMClient.getService(AuthService.class).login(CommonUtils.getLoginInfo()).setCallback(new RequestCallback<LoginInfo>() {
                            @Override
                            public void onSuccess(LoginInfo param) {
                                getView().showLoginSuccess();
                                getView().dismissProgressDialog();
                            }

                            @Override
                            public void onFailed(int code) {
                                getView().showLoginFailure("IM登录失败,错误码"+code);
                                getView().dismissProgressDialog();
                            }

                            @Override
                            public void onException(Throwable exception) {

                            }
                        });
                    }

                    @Override
                    public void onError(int errCode, String errMsg) {
                        getView().dismissProgressDialog();
                        getView().showLoginFailure(errMsg);
                    }
                });
    }
}
