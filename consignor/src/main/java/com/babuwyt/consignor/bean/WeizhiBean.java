package com.babuwyt.consignor.bean;



import com.babuwyt.consignor.entity.WeizhiEntity;
import com.babuwyt.consignor.entity.WeizhiObjEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class WeizhiBean extends BaseBean {
    private String address;
    private WeizhiObjEntity obj;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WeizhiObjEntity getObj() {
        return obj;
    }

    public void setObj(WeizhiObjEntity obj) {
        this.obj = obj;
    }
}
