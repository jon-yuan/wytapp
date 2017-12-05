package com.babuwyt.consignor.entity;

import android.text.TextUtils;

import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/14.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderEntity {
    private String orderId;
    private String orderNumber;
    private String orderSysNumber;
    private String driverName;
    private String carType;
    private String carTypeId;
    private String extractTime;
    private String realextractTime;
    private String createtime;
    private String farrivetime;
    private String routeFrom;
    private String routeTo;
    private String remark;
    private String goodsName;
    private String goodsPackType;
    private String goodsPackId;
    private int goodsNum;
    private String goodsVolume;
    private int goodsWeight;
    private String goodsHedge;
    private String orderState;
    private int type;
    private ArrayList<AddressEntity> loadAddressList;

    private ArrayList<String>  loadingPic;
    private ArrayList<String>  unloadingPic;

    public String getFarrivetime() {
        return farrivetime;
    }

    public void setFarrivetime(String farrivetime) {
        this.farrivetime = farrivetime;
    }

    public String getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(String carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getGoodsPackId() {
        return goodsPackId;
    }

    public void setGoodsPackId(String goodsPackId) {
        this.goodsPackId = goodsPackId;
    }

    public ArrayList<String> getLoadingPic() {
        return loadingPic;
    }

    public void setLoadingPic(ArrayList<String> loadingPic) {
        this.loadingPic = loadingPic;
    }

    public ArrayList<String> getUnloadingPic() {
        return unloadingPic;
    }

    public void setUnloadingPic(ArrayList<String> unloadingPic) {
        this.unloadingPic = unloadingPic;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderSysNumber() {
        return orderSysNumber;
    }

    public void setOrderSysNumber(String orderSysNumber) {
        this.orderSysNumber = orderSysNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(String extractTime) {
        this.extractTime = extractTime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public void setRouteFrom(String routeFrom) {
        this.routeFrom = routeFrom;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(String routeTo) {
        this.routeTo = routeTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPackType() {
        return goodsPackType;
    }

    public void setGoodsPackType(String goodsPackType) {
        this.goodsPackType = goodsPackType;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsVolume() {
        if (TextUtils.isEmpty(goodsVolume)){
            return "0";
        }
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public int getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(int goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsHedge() {
        return goodsHedge;
    }

    public void setGoodsHedge(String goodsHedge) {
        this.goodsHedge = goodsHedge;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public ArrayList<AddressEntity> getLoadAddressList() {
        return loadAddressList;
    }

    public void setLoadAddressList(ArrayList<AddressEntity> loadAddressList) {
        this.loadAddressList = loadAddressList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRealextractTime() {
        return realextractTime;
    }

    public void setRealextractTime(String realextractTime) {
        this.realextractTime = realextractTime;
    }
    /**
     * "orderId": 288,
     "orderNumber": "RE1998080849496",
     "orderSysNumber": null,
     "carType": 13,
     "extractTime": "2017-09-08 00:00:00.0",
     "createtime": null,
     "routeFrom": "天津市",
     "routeTo": "西安市",
     "remark": null,
     "goodsName": "组件",
     "goodsPackType": "托",
     "goodsNum": 123123,
     "goodsVolume": 123,
     "goodsWeight": 123,
     "goodsHedge": 123,
     "orderState": "已接受",
     "loadAddressList": [
     {
     "provinceName": "天津市",
     "cityName": "天津市",
     "areaName": "河西区",
     "addressDetails": "dasdadsasd",
     "addressType": "1"
     },
     {
     "provinceName": "陕西省",
     "cityName": "西安市",
     "areaName": "莲湖区",
     "addressDetails": "asdasdasda",
     "addressType": "2"
     }
     ]
     */

}
