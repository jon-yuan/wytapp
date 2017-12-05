package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.BindBankCarkEntity;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class BindBankCarkBean extends BaseBean {
    private BindBankCarkEntity obj;

    public BindBankCarkEntity getObj() {
        return obj;
    }

    public void setObj(BindBankCarkEntity obj) {
        this.obj = obj;
    }
}
