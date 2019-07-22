package com.yuyuka.billiards.ui.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.widget.StateButton;

import butterknife.BindView;

public class MapActivity extends BaseActivity implements AMap.InfoWindowAdapter {

    public static void launcher(Context context){
        Intent intent = new Intent(context,MapActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.map_view)
    public TextureMapView mMapView;
    @BindView(R.id.ll_route)
    public LinearLayout mRountLayout;
    @BindView(R.id.btn_guide)
    public StateButton mGuideBtn;
    private AMap mAMap;
    private Marker marker;

    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().setTitle("球房位置").showBack().show();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_map);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(39.858427,116.287149))
                .draggable(false).infoWindowEnable(true).snippet("登封台球俱乐部\n直线距离125m\n北京 丰台");
        mAMap.setInfoWindowAdapter(this);
        mAMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(39.858427,116.287149), 10, 0, 0)));
        mAMap.addMarker(options);
        mAMap.setOnMarkerClickListener(marker -> {
            mRountLayout.setVisibility(View.VISIBLE);
            mGuideBtn.setVisibility(View.VISIBLE);
            marker.showInfoWindow();
            return false;
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        TextView textView = new TextView(this);
        int padding = SizeUtils.dp2px(this,5);
        textView.setPadding(padding,padding,padding,padding);
        textView.setBackgroundResource(R.drawable.bg_billiards_room_photo_count);
        textView.setTextSize(12);
        textView.setTextColor(getResourceColor(R.color.white));
        textView.setText(marker.getSnippet());
        return textView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
