package com.yuyuka.billiards.ui.adapter.message;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.MessageBean;
import com.yuyuka.billiards.utils.SwipeListLayout;

import java.util.List;

public class MessageFragmentAdapter  extends BaseQuickAdapter<RecentContact, BaseViewHolder> {
    private Context mContext;
    private int position;
    private SwipeListLayout swipeListLayout;
    public MessageFragmentAdapter(Context context) {
        super(R.layout.layout_messagefragmentitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecentContact item) {
        swipeListLayout = helper.getView(R.id.sll_msg);
        helper.setText(R.id.tv_name,item.getFromNick())
                .setOnClickListener(R.id.tv_top, view -> {
                    btnOnClickListen.onTopListen(helper.getLayoutPosition(),swipeListLayout);

                })
                .setOnClickListener(R.id.tv_delete, view -> {
                    btnOnClickListen.onDeleteListen(helper.getLayoutPosition(),swipeListLayout);

                })
                .setOnClickListener(R.id.ll_msg, view -> {

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
