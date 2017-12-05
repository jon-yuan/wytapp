package com.babuwyt.consignor.entity;

import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.bigkoo.pickerview.model.IPickerViewData;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/13.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AreaEntity implements Serializable ,IPickerViewData {

    private String areaId;
    private String areaName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String getPickerViewText() {
        return this.areaName;
    }
    /**
     *  "areaId": 110101,
     "areaName": "东城区"
     */
}
