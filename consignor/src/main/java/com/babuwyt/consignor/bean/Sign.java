package com.babuwyt.consignor.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/11.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Sign extends BaseBean {
    private String obj;
    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
