package com.babuwyt.daili.bean;

import com.babuwyt.daili.entity.numEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class NumBean extends BaseBean {
    private numEntity obj;

    public numEntity getObj() {
        return obj;
    }

    public void setObj(numEntity obj) {
        this.obj = obj;
    }
}
