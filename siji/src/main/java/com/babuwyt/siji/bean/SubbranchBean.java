package com.babuwyt.siji.bean;

import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;
import com.babuwyt.siji.entity.BankEntity;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/10/24.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class SubbranchBean extends BaseBean {
    private BankEntity obj;

}
