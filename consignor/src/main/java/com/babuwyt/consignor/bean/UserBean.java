package com.babuwyt.consignor.bean;

import com.babuwyt.consignor.entity.UserInfoEntity;
import com.babuwyt.jonylibrary.bean.BaseBean;
import com.babuwyt.jonylibrary.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/5.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class UserBean extends BaseBean{

    private UserInfoEntity obj;

    public UserInfoEntity getObj() {
        return obj;
    }

    public void setObj(UserInfoEntity obj) {
        this.obj = obj;
    }
}
