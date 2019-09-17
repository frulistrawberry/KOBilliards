package com.yuyuka.billiards.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.SizeUtils;

public class CommentDialog extends Dialog {

    EditText editText;
    TextView textView;

    OnCommentListener onCommentListener;

    public interface OnCommentListener{
        void onComment(String content);
    }

    public void setOnCommentListener(OnCommentListener onCommentListener) {
        this.onCommentListener = onCommentListener;
    }

    public CommentDialog(@NonNull Context context) {
        this(context, R.style.BottomDialog);
    }

    public CommentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_comment, null);
        editText = contentView.findViewById(R.id.et_comment);
        textView = contentView.findViewById(R.id.btn_release);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    textView.setTextColor(getContext().getResources().getColor(R.color.text_color_6));
                    textView.setClickable(true);
                }else {
                    textView.setTextColor(getContext().getResources().getColor(R.color.text_color_12));
                    textView.setClickable(false);
                }
            }
        });

        textView.setOnClickListener(v -> {
            String content = editText.getText().toString();
            onCommentListener.onComment(content);
        });

        editText.requestFocus();
        setContentView(contentView);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width=(getWindow().getWindowManager().getDefaultDisplay().getWidth());
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}
