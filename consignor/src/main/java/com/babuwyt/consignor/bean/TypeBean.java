package com.babuwyt.consignor.bean;

import com.babuwyt.consignor.entity.TypeEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/13.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TypeBean extends BaseBean {
    private ArrayList<TypeEntity> obj;

    public TypeBean(ArrayList<TypeEntity> obj) {
        this.obj = obj;
    }

    public ArrayList<TypeEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<TypeEntity> obj) {
        this.obj = obj;
    }
}


