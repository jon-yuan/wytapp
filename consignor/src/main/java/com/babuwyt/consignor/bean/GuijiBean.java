package com.babuwyt.consignor.bean;

import com.babuwyt.consignor.entity.LoadpickEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/30.
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
