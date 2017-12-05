package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.LookPicEntity;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class LookPicBean extends BaseBean {
    private ArrayList<LookPicEntity> obj;

    public ArrayList<LookPicEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<LookPicEntity> obj) {
        this.obj = obj;
    }
}
