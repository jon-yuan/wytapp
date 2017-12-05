package com.babuwyt.daili.bean;

import com.babuwyt.daili.entity.MainEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/6/28.
 */

@HttpResponse(parser = JsonResponseParser.class)
public class MainBean extends BaseBean {
    private ArrayList<MainEntity> obj;

    public ArrayList<MainEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<MainEntity> obj) {
        this.obj = obj;
    }
}
