package com.yuyuka.billiards.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.zyyoona7.lib.BaseCustomPopup;

import java.util.List;

public class ListPop extends BaseCustomPopup {

    int[]icons = {R.mipmap.tuwen,R.mipmap.wenzhang,R.mipmap.xiaoshipin,R.mipmap.xiaoshipin};
    private OnItemClickListener mListener;
    private LinearLayout mParentLayout;

    public ListPop(Context context) {
        super(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_pop_list);
        setWidth(SizeUtils.dp2px(getContext(),100));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
    }

    @Override
    protected void initViews(View view) {
        mParentLayout = getView(R.id.layout_parent);
    }

    public ListPop setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
        return this;
    }

    public ListPop setData(List<String> data){
        if (!CollectionUtils.isEmpty(data)){
            mParentLayout.removeAllViews();
            for (int i = 0; i < data.size(); i++) {
                View child = LayoutInflater.from(getContext()).inflate(R.layout.item_pop_list,mParentLayout, false);
                TextView textView = child.findViewById(R.id.tv_text);
                ImageView imageView = child.findViewById(R.id.iv_img);
                textView.setText(data.get(i));
                imageView.setImageResource(icons[i]);
                if (i == 0){
                    child.findViewById(R.id.v_divider).setVisibility(View.GONE);
                }
                final int finalI = i;
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener!=null)
                            mListener.onItemClick(finalI);
                    }
                });
                mParentLayout.addView(child);
            }
        }
        return this;
    }

   public  interface OnItemClickListener{
        void onItemClick(int position);
   }
}