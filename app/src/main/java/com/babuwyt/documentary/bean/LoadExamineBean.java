package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.LoadExamineEntity;
import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LoadExamineBean extends BaseBean {
    private ArrayList<String> obj;

    public ArrayList<String> getObj() {
        return obj;
    }

    public void setObj(ArrayList<String> obj) {
        this.obj = obj;
    }
}
