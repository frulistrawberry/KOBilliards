package com.yuyuka.billiards.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.SiRuoZhiyeBean;
import com.yuyuka.billiards.ui.adapter.ranking.SiRuoZhiyeAdapter;
import com.yuyuka.billiards.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class ZhiweiDialog {
    private Dialog mDialog;
    private View mDialogView;
    List<SiRuoZhiyeBean> siRuoZhiyeBeans;
    private RecyclerView recyclerView;
    private PtrClassicFrameLayout layoutPtr;
    private ImageView icClean;
    SiRuoZhiyeAdapter adapter;
    public ZhiweiDialog( Context mContext){
        mDialog = new Dialog(mContext, R.style.DialogTheme);
        mDialogView = View.inflate(mContext,R.layout.layout_zhiyebangxiang,null);
        recyclerView=mDialogView.findViewById(R.id.recycler_view);
        layoutPtr=mDialog.findViewById(R.id.layout_ptr);
        icClean=mDialog.findViewById(R.id.ic_clean);
        mDialog.setContentView(mDialogView);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        initDialog();
        for (int i = 0; i < 3; i++) {
            SiRuoZhiyeBean siRuoZhiyeBean = new SiRuoZhiyeBean();
            siRuoZhiyeBeans.add(siRuoZhiyeBean);
        }
        adapter.setNewData(siRuoZhiyeBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

    }
    private void initDialog() {
        siRuoZhiyeBeans=new ArrayList<>();
        adapter=new SiRuoZhiyeAdapter();
    }

    public void dismiss(){
        if (mDialog != null&&mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public Window getWindow(){
        return mDialog.getWindow();
    }

    public void show(){
        if (mDialog != null) {
            mDialog.show();
        }
    }
}

