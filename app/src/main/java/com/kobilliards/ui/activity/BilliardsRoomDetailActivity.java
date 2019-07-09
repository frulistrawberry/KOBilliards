package com.kobilliards.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kobilliards.R;
import com.kobilliards.base.BaseActivity;
import com.kobilliards.widget.tabindicator.MagicIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.CommonNavigator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.kobilliards.widget.tabindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.w3c.dom.Text;

import butterknife.BindView;

public class BilliardsRoomDetailActivity extends BaseActivity {


    private String[] mTitle = {"球台预定","球房活动","球友点评","设施亮点"};
    @BindView(R.id.tab_layout)
    MagicIndicator mTabLayout;

    public static void launcher(Context context){
        Intent intent = new Intent(context,BilliardsRoomDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房详情").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_billiards_detail);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return  mTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResourceColor(R.color.text_color_1));
                simplePagerTitleView.setSelectedColor(getResourceColor(R.color.text_color_2));
                simplePagerTitleView.setText(mTitle[index]);
                simplePagerTitleView.setOnClickListener(v -> {

                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResourceColor(R.color.tab_indicator_start_color),getResourceColor(R.color.tab_indicator_end_color));
                return linePagerIndicator;
            }
        });
        mTabLayout.setNavigator(commonNavigator);
    }

    @Override
    protected void initData() {

    }
}
