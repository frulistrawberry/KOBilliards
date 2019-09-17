package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.widget.StateButton;
import com.yuyuka.billiards.widget.TagLayout;


public class FilterDialog extends Dialog {
    int quickCondition = -1;
    int lowPrice = -1;
    int highPrice = -1;
    int releaseTimeCondition = -1;
    int otherCondition = -1;
    TagLayout quickTag;
    TagLayout releaseTimeTag;
    TagLayout otherTag;

    OnFilterListenter onFilterListenter;
    EditText lowPriceEt;
    EditText highPriceEt;

    public void setOnFilterListenter(OnFilterListenter onFilterListenter) {
        this.onFilterListenter = onFilterListenter;
    }

    public FilterDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_goods_filter,null);
        contentView.requestFocus();
        setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width= (int) ((getWindow().getWindowManager().getDefaultDisplay().getWidth()) * 0.9);
        params.height= WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setGravity(Gravity.RIGHT);
        getWindow().setWindowAnimations(R.style.RightAnimation);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        quickTag = contentView.findViewById(R.id.tag_quick);
        for (int i = 0; i < quickTag.getChildCount(); i++) {
            TextView textView = (TextView) quickTag.getChildAt(i);
            int finalI = i;
            textView.setOnClickListener(v -> {
                for (int i1 = 0; i1 < quickTag.getChildCount(); i1++) {
                    TextView textView1 = (TextView) quickTag.getChildAt(i1);
                    textView1.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_negative));
                }
                textView.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_positive));
                quickCondition = finalI;
            });

        }

        releaseTimeTag = contentView.findViewById(R.id.tag_release);
        for (int i = 0; i < releaseTimeTag.getChildCount(); i++) {
            TextView textView = (TextView) releaseTimeTag.getChildAt(i);
            int finalI = i;
            textView.setOnClickListener(v -> {
                for (int i1 = 0; i1 < releaseTimeTag.getChildCount(); i1++) {
                    TextView textView1 = (TextView) releaseTimeTag.getChildAt(i1);
                    textView1.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_negative));
                }
                textView.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_positive));
                releaseTimeCondition = finalI;
            });

        }

        otherTag = contentView.findViewById(R.id.tag_other);
        for (int i = 0; i < otherTag.getChildCount(); i++) {
            TextView textView = (TextView) otherTag.getChildAt(i);
            int finalI = i;
            textView.setOnClickListener(v -> {
                for (int i1 = 0; i1 < otherTag.getChildCount(); i1++) {
                    TextView textView1 = (TextView) otherTag.getChildAt(i1);
                    textView1.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_negative));
                }
                textView.setBackgroundDrawable(getContext().getDrawable(R.drawable.bg_tag_comment_positive));
                otherCondition = finalI;
            });

        }

        lowPriceEt = contentView.findViewById(R.id.et_low);
        highPriceEt = contentView.findViewById(R.id.et_high);
        StateButton commitBtn = contentView.findViewById(R.id.btn_complete);
        StateButton resetBtn = contentView.findViewById(R.id.btn_reset);
        commitBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(highPriceEt.getText().toString()) && !TextUtils.isEmpty(lowPriceEt.getText().toString())){
                highPrice = Integer.valueOf(highPriceEt.getText().toString());
                lowPrice = Integer.valueOf(lowPriceEt.getText().toString());
            }
            onFilterListenter.onFilter(quickCondition,lowPrice,highPrice,releaseTimeCondition,otherCondition);
        });
        resetBtn.setOnClickListener(v -> {
            lowPriceEt.setText("");
            lowPriceEt.clearFocus();
            highPriceEt.setText("");
            highPriceEt.clearFocus();
            highPrice = -1;
            lowPrice = -1;
            quickCondition = -1;
            releaseTimeCondition = -1;
            otherCondition = -1;

            for (int i = 0; i < quickTag.getChildCount(); i++) {
                quickTag.getChildAt(i).setBackgroundResource(R.drawable.bg_tag_comment_negative);
            }
            for (int i = 0; i < releaseTimeTag.getChildCount(); i++) {
                releaseTimeTag.getChildAt(i).setBackgroundResource(R.drawable.bg_tag_comment_negative);
            }
            for (int i = 0; i < otherTag.getChildCount(); i++) {
                otherTag.getChildAt(i).setBackgroundResource(R.drawable.bg_tag_comment_negative);
            }
        });
    }

    public interface OnFilterListenter{
        void onFilter(int quickCondition,int lowPrice,int hightPrice,int releaseTimeCondition,int otherCondition);
    }

    public void clearFocus(){
        lowPriceEt.clearFocus();
        highPriceEt.clearFocus();
    }





}
