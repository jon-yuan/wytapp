package com.babuwyt.consignor.bean;

import com.babuwyt.consignor.entity.OrderEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/14.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class OrderListBean extends BaseBean {

    private ArrayList<OrderEntity> obj;

    public ArrayList<OrderEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<OrderEntity> obj) {
        this.obj = obj;
    }
}
