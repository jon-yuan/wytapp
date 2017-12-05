package com.babuwyt.consignor.bean;

import com.babuwyt.consignor.entity.Address;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AddressList extends BaseBean {

    private ArrayList<Address> obj;

    public ArrayList<Address> getObj() {
        return obj;
    }

    public void setObj(ArrayList<Address> obj) {
        this.obj = obj;
    }
}
