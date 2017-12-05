package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.SignPicEntity;
import com.babuwyt.documentary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SignPicBean extends BaseBean {
    private ArrayList<SignPicEntity> obj;

    public ArrayList<SignPicEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<SignPicEntity> obj) {
        this.obj = obj;
    }
}
