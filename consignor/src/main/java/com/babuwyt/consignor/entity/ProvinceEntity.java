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
public class ProvinceEntity implements Serializable,IPickerViewData {
    private String provinceId;
    private String provinceName;
    private ArrayList<CityEntity> city;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public ArrayList<CityEntity> getCity() {
        return city;
    }

    public void setCity(ArrayList<CityEntity> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.provinceName;
    }
    /**
     *  "provinceId": 11,
     "provinceName": "北京市",
     "city": [
     */
}
