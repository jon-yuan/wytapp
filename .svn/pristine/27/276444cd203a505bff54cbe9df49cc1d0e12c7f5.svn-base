package com.babuwyt.consignor.entity;

import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.bigkoo.pickerview.model.IPickerViewData;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/13.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CityEntity implements Serializable ,IPickerViewData {
    private String cityId;
    private String cityName;
    private ArrayList<AreaEntity> area;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public ArrayList<AreaEntity> getArea() {
        return area;
    }

    public void setArea(ArrayList<AreaEntity> area) {
        this.area = area;
    }

    @Override
    public String getPickerViewText() {
        return this.cityName;
    }
    /**
     * "cityId": 1101,
     "cityName": "北京市",
     "area":
     */
}
