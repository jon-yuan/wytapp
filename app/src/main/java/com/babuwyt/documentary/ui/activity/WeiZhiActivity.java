package com.babuwyt.documentary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.babuwyt.documentary.R;
import com.babuwyt.documentary.base.BaseActivity;
import com.babuwyt.documentary.bean.WeizhiBean;
import com.babuwyt.documentary.finals.BaseURL;
import com.babuwyt.documentary.inteface.MyCallBack;
import com.babuwyt.documentary.util.UHelper;
import com.babuwyt.documentary.util.XUtil;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import amap.InfoWinAdapter;
import amap.MapUtil;

/**
 * Created by lenovo on 2017/7/19.
 * 实时位置
 */
@ContentView(R.layout.activity_weizhi)
public class WeiZhiActivity extends BaseActivity{
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.mapView)
    MapView mapView;
    private AMap aMap;
    private InfoWinAdapter mAdapter;
    private Marker oldMarker;
    private String fsendcarno;
    private String name;
    MapUtil mapUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        fsendcarno=getIntent().getStringExtra("fsendcarno");
        name=getIntent().getStringExtra("name");
        init();
        initMap();
        getHttp();
    }
    private void init() {
        toolbar.setTitle(getString(R.string.shishiweizhi));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    //设置地图
    private void initMap() {
        aMap=mapView.getMap();
        mAdapter=new InfoWinAdapter(this);
       mapUtil=MapUtil.getInstance(this,aMap);
        mapUtil.setMapZoomTo(20);
        aMap.setInfoWindowAdapter(mAdapter);

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

    private Marker addMarkerToMap(LatLng latLng, String title, String snippet) {
        return aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_car))
        );
    }

    private void getHttp(){
        ArrayList<String> list=new ArrayList<String>();
        list.add(fsendcarno);
//        list.add("D11020170721001");
        XUtil.GetPing(BaseURL.GETLOCATION_BY_SEND_CARORDER,list,new MyCallBack<WeizhiBean>(){
            @Override
            public void onSuccess(WeizhiBean result) {
                super.onSuccess(result);
                Gson gson=new Gson();
                String s=gson.toJson(result);
                Log.d("==result==",s);
                if (result.isSuccess()){
                    Marker marker=addMarkerToMap(new LatLng(result.getObj().getWgLat(), result.getObj().getWgLon()),"","");
                    mapUtil.moveMapCenter(new LatLng(result.getObj().getWgLat(), result.getObj().getWgLon()));
                    marker.showInfoWindow();
                    mAdapter.setData(name,result.getAddress());
                    UHelper.showToast(WeiZhiActivity.this,result.getMsg());
                }else {
                    UHelper.showToast(WeiZhiActivity.this,result.getMsg());
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Log.d("服务器异常",ex+"");
                UHelper.showToast(WeiZhiActivity.this,getString(R.string.NET_ERROR));
            }
        });
    }
}
