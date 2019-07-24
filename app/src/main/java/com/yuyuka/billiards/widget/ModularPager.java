package com.yuyuka.billiards.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yuyuka.billiards.pojo.ModularPojo;
import com.yuyuka.billiards.ui.adapter.home.ModularAdapter;
import com.yuyuka.billiards.utils.CollectionUtils;

import java.util.List;

public class ModularPager extends ViewPager {


    ViewPagerAdapter mAdapter;

    public ModularPager(@NonNull Context context) {
        this(context,null);
    }

    public ModularPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(List<ModularPojo> data){
        mAdapter = new ViewPagerAdapter(data,getContext());
        setAdapter(mAdapter);
    }

    class ViewPagerAdapter extends PagerAdapter {
        List<ModularPojo> mData;
        Context mContext;

        public ViewPagerAdapter(List<ModularPojo> data, Context context) {
            this.mData = data;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            if (CollectionUtils.isEmpty(mData))
                return 0;
            else if (mData.size()%8 == 0)
                return mData.size()/8;
            else
                return mData.size()/8+1;

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            RecyclerView view = new RecyclerView(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setLayoutManager(new GridLayoutManager(mContext,4));
            view.setTag(position);
            ModularAdapter adapter = new ModularAdapter();
            view.setAdapter(adapter);
            if (position<mData.size()/8){
                adapter.setNewData(mData.subList(8*position,8*position+8));
            }else {
                adapter.setNewData(mData.subList(8*position,(8*position)+(mData.size()%8)));
            }
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
