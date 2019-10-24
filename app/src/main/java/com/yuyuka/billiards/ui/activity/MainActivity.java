package com.yuyuka.billiards.ui.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.netease.nim.uikit.common.CommonUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.model.CustomNotification;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.ActivityManager;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.activity.login.LoginActivity;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.BetFragment;
import com.yuyuka.billiards.ui.fragment.HomeFragment;
import com.yuyuka.billiards.ui.fragment.MineFragment;
import com.yuyuka.billiards.ui.fragment.NewsFragment;
import com.yuyuka.billiards.utils.CommonUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.TabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements TabBar.OnTabCheckListener {


    @BindView(R.id.tab_indicator)
    TabBar mTabIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    RxPermissions mPermissions;
    List<Fragment> mFragmentList;
    PagerAdapter mAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mTabIndicator.addTab(new TabBar.Tab()
                .setCheckedColor(getResourceColor(R.color.text_color_6))
                .setColor(getResourceColor(R.color.text_color_1))
                .setNormalIcon(R.mipmap.ic_home_un_selected)
                .setPressedIcon(R.mipmap.ic_home_selected)
                .setText("首页"))
                .addTab(new TabBar.Tab()
                        .setCheckedColor(getResourceColor(R.color.text_color_6))
                        .setColor(getResourceColor(R.color.text_color_1))
                        .setNormalIcon(R.mipmap.ic_bet_un_selected)
                        .setPressedIcon(R.mipmap.ic_bet_selected)
                        .setText("约局"))
                .addTab(new TabBar.Tab()
                        .setCheckedColor(getResourceColor(R.color.text_color_6))
                        .setColor(getResourceColor(R.color.text_color_1))
                        .setNormalIcon(R.mipmap.ic_news_un_selected)
                        .setPressedIcon(R.mipmap.ic_news_selected)
                        .setText("资讯"))
                .addTab(new TabBar.Tab()
                        .setCheckedColor(getResourceColor(R.color.text_color_6))
                        .setColor(getResourceColor(R.color.text_color_1))
                        .setNormalIcon(R.mipmap.ic_mine_un_selected)
                        .setPressedIcon(R.mipmap.ic_mine_selected)
                        .setText("我的"))
                .setOnTabCheckListener(this);
        requestPermissions();
        initIM();

    }

    private void initIM(){

        if (NIMClient.getStatus().wontAutoLogin() || NIMClient.getStatus() == StatusCode.UNLOGIN){
            CommonUtils.clearUserInfo();
            startActivity(new Intent(ActivityManager.getInstance().getCurrentActivity(), LoginActivity.class));
        }




    }

    @Override
    protected void initData() {
        mPermissions = new RxPermissions(this);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new BetFragment());
        mFragmentList.add(new NewsFragment());
        mFragmentList.add(new MineFragment());
        mAdapter = new PagerAdapter(getSupportFragmentManager(),mFragmentList);

    }

    @SuppressLint("CheckResult")
    private void requestPermissions(){
        mPermissions
                .requestEachCombined(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE )
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意所有权限
                        mViewPager.setAdapter(mAdapter);
                        mTabIndicator.setCurrentItem(0);
                        mViewPager.setOffscreenPageLimit(4);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                        requestPermissions();
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』
                        new AlertDialog.Builder(this)
                                .setCancelable(false)
                                .setTitle("提示")
                                .setMessage("为保证APP正常运行，需要您授予部分权限，是否前往设置页面设置？")
                                .setPositiveButton("是", (dialog, which) -> {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",getContext().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent,REQUEST_CODE_SETTING);
                                })
                                .setNegativeButton("否", (dialog, which) -> finish())
                                .create()
                                .show();

                    }
                });
    }

    @Override
    public void onTabSelected(View v, int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SETTING)
            requestPermissions();

    }

}
