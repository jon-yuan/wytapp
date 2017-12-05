package com.babuwyt.documentary.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapException;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.babuwyt.documentary.R;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.bean.GuijiBean;
import com.babuwyt.documentary.entity.resultEntity;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.util.XUtil;
import com.babuwyt.jonylibrary.bean.Gps;
import com.babuwyt.jonylibrary.inteface.MyCallBack;
import com.babuwyt.jonylibrary.util.UHelper;
import com.babuwyt.jonylibrary.util.ZuobiaoUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import amap.MapUtil;

/**
 * Created by lenovo on 2017/7/18.
 */
@ContentView(R.layout.activity_guiji)
public class GuijiActivity extends BaseActivity {



    @ViewInject(R.id.mapview)
    MapView mapView;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    private AMap aMap;
    private Polyline polyline;
    private String fid;
    private ArrayList<LatLng> latLngs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        fid = getIntent().getStringExtra("fid");
        init();
        initMap();
        getGuiji();
    }

    private void initMap() {
        aMap = mapView.getMap();
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(10));
//        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
        latLngs = new ArrayList<LatLng>();

        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
            }
        });
    }
    public void setMarker(LatLng latLng, int drawID) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), drawID)));
        markerOptions.visible(true);
        aMap.addMarker(markerOptions);
    }

    private void init() {
        toolbar.setTitle(getString(R.string.xingshiguiji));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getGuiji() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(fid);
        loadingDialog.showDialog();
        XUtil.GetPing(BaseURL.GUIJI, list, new MyCallBack<GuijiBean>() {
            @Override
            public void onSuccess(GuijiBean o) {
                UHelper.showToast(GuijiActivity.this, o.getMsg());
                loadingDialog.dissDialog();
                if (o.isSuccess()) {
                    latLngs.clear();
                    if (o.getObj().getResult()==null || o.getObj().getResult().size()<=0){
                        return;
                    }
                    for (resultEntity entity:o.getObj().getResult()){
                        latLngs.add(new LatLng(entity.getWgLat(),entity.getWgLon()));
                    }
                    MapUtil mapUtil = MapUtil.getInstance(GuijiActivity.this, aMap);
                    ArrayList<MarkerOptions> markerOptions = new ArrayList<MarkerOptions>();
                    markerOptions.add(mapUtil.setMarker(latLngs.get(0), R.drawable.marker_start, false));
                    markerOptions.add(mapUtil.setMarker(latLngs.get(latLngs.size() - 1), R.drawable.marker_end, false));
                    mapUtil.addMarkers(markerOptions);
                    mapUtil.setPolyline(latLngs);
                    mapUtil.setMapwithBounds(latLngs, 20);
                }

            }

            @Override
            public void onError (Throwable ex,boolean isOnCallback){
                loadingDialog.dissDialog();
                Log.d("获取到的轨迹异常",ex+"");
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }
}
