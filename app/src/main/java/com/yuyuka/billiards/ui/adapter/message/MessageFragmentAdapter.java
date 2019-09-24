package com.yuyuka.billiards.ui.adapter.message;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.MessageBean;
import com.yuyuka.billiards.utils.SwipeListLayout;

import java.util.List;

public class MessageFragmentAdapter  extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    private Context mContext;
    private int position;
    private SwipeListLayout swipeListLayout;
    public MessageFragmentAdapter(Context context) {
        super(R.layout.layout_messagefragmentitem);
        this.mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        swipeListLayout = helper.getView(R.id.sll_msg);
        helper.setText(R.id.tv_name,item.getTvName())
                .setOnClickListener(R.id.tv_top, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnOnClickListen.onTopListen(helper.getLayoutPosition(),swipeListLayout);
                        Log.e("position==",helper.getLayoutPosition()+"");
                    }
                })
                .setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnOnClickListen.onDeleteListen(helper.getLayoutPosition(),swipeListLayout);
                        Log.e("position==",helper.getLayoutPosition()+"");

                    }
                })
                .setOnClickListener(R.id.ll_msg, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,"正常区域",Toast.LENGTH_SHORT).show();
                        Log.e("position==",helper.getLayoutPosition()+"");
                    }
                });
    }

    //定义回调借口，将置顶按钮和删除按钮设置回调
    public interface  BtnOnClickListen{
        void onTopListen(int position ,SwipeListLayout swipeListLayout);
        void onDeleteListen(int position ,SwipeListLayout swipeListLayout);
    }

    /**
     * 点击，设置回调
     */
    public BtnOnClickListen btnOnClickListen;

    /**
     * 设置监听接口
     * @param btnOnClickListen
     */
    public void setBtnOnClickListen(BtnOnClickListen btnOnClickListen) {
        this.btnOnClickListen = btnOnClickListen;
    }
}
