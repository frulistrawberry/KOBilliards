package com.kobilliards.ui.fragment.nearbyroom;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kobilliards.R;
import com.kobilliards.base.BaseFragment;
import com.kobilliards.pojo.BilliardsRoomPojo;
import com.kobilliards.ui.adapter.BilliardsRoomAdapter;
import com.kobilliards.widget.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RecommendRoomFragment extends BaseFragment {
    @BindView(R.id.drop_down_menu)
    DropDownMenu mDropDownMenu;
    private List<View> popupViews = new ArrayList<>();
    View mContentView;
    PtrClassicFrameLayout mPtrLayout;
    RecyclerView mRecyclerView;
    BilliardsRoomAdapter mAdapter;

    String[] mMenuTitles = {"我附近","星级/价格","筛选"};
    private String locations[] = {"1000m", "2000m", "3000m", "4000m", "5000m"};
    private String levels[] = {"按星级升序", "按星级降序", "按价格升序", "按价格降序"};
    private String filters[] = {"筛选1", "筛选2", "筛选3", "筛选4", "筛选5"};

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_recommend_room,parent,false);
    }

    @Override
    protected void initData() {
        mAdapter = new BilliardsRoomAdapter();
    }

    @Override
    protected void initView() {
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.include_ptr_recycler,null);
        mPtrLayout = mContentView.findViewById(R.id.layout_ptr);
        mRecyclerView = mContentView.findViewById(R.id.recycler_view);

        mPtrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrLayout.refreshComplete();
            }
        });

        final ListView locationView = new ListView(getContext());
        locationView.setDividerHeight(0);
        ListDropDownAdapter ageAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(locations));
        locationView.setAdapter(ageAdapter);
        final ListView levelView = new ListView(getContext());
        levelView.setDividerHeight(0);
        ListDropDownAdapter levelAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(levels));
        levelView.setAdapter(levelAdapter);
        final ListView filterView = new ListView(getContext());
        filterView.setDividerHeight(0);
        ListDropDownAdapter filterAdapter = new ListDropDownAdapter(getContext(), Arrays.asList(filters));
        filterView.setAdapter(filterAdapter);
        popupViews.add(locationView);
        popupViews.add(levelView);
        popupViews.add(filterView);
        mDropDownMenu.setDropDownMenu(Arrays.asList(mMenuTitles),popupViews,mContentView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        List<BilliardsRoomPojo> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new BilliardsRoomPojo());
        }
        mAdapter.setNewData(data);

    }

    static class ListDropDownAdapter extends BaseAdapter {

        private Context context;
        private List<String> list;
        private int checkItemPosition = 0;

        public void setCheckItem(int position) {
            checkItemPosition = position;
            notifyDataSetChanged();
        }

        public ListDropDownAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_default_drop_down, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            fillValue(position, viewHolder);
            return convertView;
        }

        private void fillValue(int position, ViewHolder viewHolder) {
            viewHolder.mText.setText(list.get(position));
            if (checkItemPosition != -1) {
                if (checkItemPosition == position) {
                    viewHolder.mText.setTextColor(Color.RED);
                    viewHolder.mText.setBackgroundColor(Color.GRAY);
                } else {
                    viewHolder.mText.setTextColor(Color.BLACK);
                    viewHolder.mText.setBackgroundColor(Color.WHITE);
                }
            }
        }

        static class ViewHolder {
            @BindView(R.id.text)
            TextView mText;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
