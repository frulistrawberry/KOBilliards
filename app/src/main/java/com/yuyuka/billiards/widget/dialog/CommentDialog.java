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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.SizeUtils;

import java.util.Timer;
import java.util.TimerTask;

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
                    textView.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bg_news_attention_orange));
                    textView.setClickable(true);
                }else {
                    textView.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bg_news_attention_gray));
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

    private void showSoftInput(EditText etIpAddress) {
        //自动获取焦点
        etIpAddress.setFocusable(true);
        etIpAddress.setFocusableInTouchMode(true);
        etIpAddress.requestFocus();
        //200毫秒后弹出软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) etIpAddress.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(etIpAddress, 0);
            }
        }, 200);
    }

    public void setHint(String hint){
        editText.setHint(hint);
    }

    @Override
    public void show() {
        super.show();
        showSoftInput(editText);

    }
}
