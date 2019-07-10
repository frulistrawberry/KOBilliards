package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;

public class CommentActivity  extends BaseActivity {
    public static void launcher(Context context){
        Intent intent = new Intent(context,CommentActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房名称").showBack().show();
    }

    @Override
    protected void initView() {
     setContentView(R.layout.layout_comment_activity);
    }

    @Override
    protected void initData() {

    }
}
