package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.GuijiEntity;
import com.babuwyt.documentary.entity.LoadpickEntity;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.jonylibrary.bean.BaseBean;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class GuijiBean extends BaseBean {



    private LoadpickEntity obj;

    public LoadpickEntity getObj() {
        return obj;
    }

    public void setObj(LoadpickEntity obj) {
        this.obj = obj;
    }
}
