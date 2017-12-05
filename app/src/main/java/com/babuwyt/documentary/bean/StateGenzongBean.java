package com.babuwyt.documentary.bean;

import com.babuwyt.documentary.entity.StateGenzongEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class StateGenzongBean extends BaseBean {
    private StateGenzongBaseBean obj;


    public StateGenzongBaseBean getObj() {
        return obj;
    }

    public void setObj(StateGenzongBaseBean obj) {
        this.obj = obj;
    }
}
