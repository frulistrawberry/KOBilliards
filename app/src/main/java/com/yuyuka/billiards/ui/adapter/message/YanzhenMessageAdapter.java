package com.yuyuka.billiards.ui.adapter.message;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.pojo.YanzhengBean;

public class YanzhenMessageAdapter extends BaseSectionQuickAdapter<YanzhengBean, BaseViewHolder> {
    public YanzhenMessageAdapter() {
        super(R.layout.layout_yanzhenitem,R.layout.layout_yanzhengheader,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, YanzhengBean item) {
    }

    @Override
    protected void convertHead(BaseViewHolder helper, YanzhengBean item) {
            helper.setText(R.id.heaer,"一天前");
    }
}
