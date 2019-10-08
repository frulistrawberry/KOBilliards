package com.yuyuka.billiards.ui.activity.news;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.presenter.news.ReleasePresenter;

public class ReleaseVideoActivity extends BaseMvpActivity<ReleasePresenter> {
    @Override
    protected ReleasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_video_picker);
    }

    @Override
    protected void initData() {

    }
}
