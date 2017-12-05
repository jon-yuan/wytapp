package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.BankEntity;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class BankListBean extends BaseBean {
    private ArrayList<BankEntity> obj;

    public ArrayList<BankEntity> getObj() {
        return obj;
    }

    public void setObj(ArrayList<BankEntity> obj) {
        this.obj = obj;
    }
}
