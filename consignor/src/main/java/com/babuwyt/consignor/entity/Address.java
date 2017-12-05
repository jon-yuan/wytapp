package com.babuwyt.consignor.entity;

import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.bigkoo.pickerview.model.IPickerViewData;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/13.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Address implements Serializable, IPickerViewData {
    private String fid;
    private String forwarderId;
    private String fAddress;
    private String fLinkMan;
    private String fPhone;
    private String fareaNo;
    private String faddressDetails;
    private String cityName;
    private String areaName;
    private String provinceName;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(String forwarderId) {
        this.forwarderId = forwarderId;
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getfLinkMan() {
        return fLinkMan;
    }

    public void setfLinkMan(String fLinkMan) {
        this.fLinkMan = fLinkMan;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public String getFareaNo() {
        return fareaNo;
    }

    public void setFareaNo(String fareaNo) {
        this.fareaNo = fareaNo;
    }

    public String getFaddressDetails() {
        return faddressDetails;
    }

    public void setFaddressDetails(String faddressDetails) {
        this.faddressDetails = faddressDetails;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String getPickerViewText() {
        return this.cityName+this.areaName;
    }

    /**
 *   "fid": 1,
 "forwarderId": 24,
 "fAddress": "宁夏回族自治区银川市西夏区开元东路15号",
 "fLinkMan": "张早强",
 "fPhone": "13895016042",
 "fareaNo": "640105",
 "faddressDetails": "开元东路15号",
 "cityName": "银川市",
 "areaName": "西夏区",
 "provinceName": "宁夏回族自治区"
 */
}
