package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.LookAddressEntity;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LookAddressBean extends BaseBean{
    private ArrayList<LookAddressEntity> obj;

    public ArrayList<LookAddressEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<LookAddressEntity> obj) {
        this.obj = obj;
    }
}
