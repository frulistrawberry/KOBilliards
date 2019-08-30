package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.SelectTimePojo;
import com.yuyuka.billiards.ui.activity.merchant.OrderConfirmActivity;
import com.yuyuka.billiards.ui.adapter.room.SelectTimeAdapter;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.StateButton;

import java.util.List;

public class SelectTimeDialog extends Dialog implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {
    private SelectTimeAdapter mAdapter;
    private List<SelectTimePojo> mData;
    private StateButton reserveBtn;

    public SelectTimeDialog( Context context) {
        this(context,R.style.BottomDialog);
    }

    public SelectTimeDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAdapter = new SelectTimeAdapter();
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_select_time, null);
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
        reserveBtn = contentView.findViewById(R.id.btn_reserve);
        reserveBtn.setOnClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width=(getWindow().getWindowManager().getDefaultDisplay().getWidth());
        params.height= SizeUtils.dp2px(context,336);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public  void  setData(List<SelectTimePojo> data){
        this.mData = data;
        mAdapter.setNewData(mData);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        boolean hasChoose = false;
        int firstChooseP = 0;
        int lastChooseP = 0;
        for (int i = 0; i < mData.size(); i++) {
            hasChoose = mData.get(i).isSelected();
            if (hasChoose){
                firstChooseP = i;
                break;
            }
        }
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelected())
            lastChooseP = i;
        }
        if (hasChoose){
            if (mData.get(position).isSelected()){
                if (position==firstChooseP || position == lastChooseP){
                    mData.get(position).setSelected(false);
                }
            }else {
                if (Math.abs(firstChooseP-position)==1 || Math.abs(lastChooseP-position) == 1){
                    mData.get(position).setSelected(true);
                }
            }
        }else {
            mData.get(position).setSelected(true);
        }
        mAdapter.setNewData(mData);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reserve:
                OrderConfirmActivity.launcher(getContext());
                break;
        }
    }
}
