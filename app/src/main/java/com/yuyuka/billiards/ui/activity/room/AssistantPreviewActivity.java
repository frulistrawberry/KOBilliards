package com.yuyuka.billiards.ui.activity.room;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.ui.adapter.common.PagerAdapter;
import com.yuyuka.billiards.ui.fragment.room.AssistantListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class AssistantPreviewActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    public static void launcher(Context context){
        context.startActivity(new Intent(context, AssistantPreviewActivity.class));
    }

    @Override
    protected void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coach_detail);
        mStatusBar.setVisibility(View.GONE);
        List<Fragment> coachList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            coachList.add(AssistantListFragment.newFragment(1));
        }
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),coachList));
    }

    @Override
    protected void initData() {

    }
}
